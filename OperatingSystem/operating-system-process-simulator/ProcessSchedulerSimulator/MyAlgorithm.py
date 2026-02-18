from entity import Process
import math

class Algorithm:
    def __init__(self, cores, processes, ww, nw, tw):
        self.core_num = len(cores)
        self.cores = cores                       # 코어 배열
        self.processes = processes               # 프로세스 배열
        self.timeline = []                       # Index: time

        self.watt_weight = ww
        self.ntt_weight = nw
        self.time_weight = tw

        self.min_cost = float('inf')

        self.finished_processes = []

    # 어떤 코어에 프로세스가 할당되었을 경우 Watt 계산
    def calculation_Watt(self, core, process):
        if process.pid is None:                  # 프로세스가 빈 프로세스면
            watt = 0
        else:
            if core.is_running:
                watt = core.watt
            else:
                watt = core.watt + core.start_watt
        return 1.0 / (1.0 + math.exp(-(watt)))
    
    # 어떤 코어에 프로세스가 할당되었을 경우 Time 계산
    def calculation_Time(self, current_time, core, process):
        if process.pid is None:                  # 프로세스가 빈 프로세스면
            return 1
        
        # 지금까지 걸린 시간
        elapsed_time = current_time - process.arrival_time
        
        # 평균 처리 속도 갱신
        if process.start_time == None:
            predicted_speed = 0
        else:
            predicted_speed = (process.performed_work + core.performance) / (current_time - process.start_time + 1)
        
        # 남은 일량에 기반한 예측 남은 시간
        remaining_work = max(process.work_load - (process.performed_work + core.performance), 0)
        predicted_remaining_time = remaining_work / (predicted_speed + 1)
        
        # 예측 전체 소요 시간 (현재까지 경과 + 남은 시간)
        predicted_TT = elapsed_time + predicted_remaining_time

        return 1.0 / (1.0 + math.exp((process.predicted_TT - predicted_TT)))

    # 어떤 코어에 프로세스가 할당되었을 경우 Time 계산 
    def calculation_NTT(self, current_time, core, process):
        if process.pid is None:
            return 1.0

        # 지금까지 걸린 시간
        elapsed_time = current_time - process.arrival_time
        
        # 평균 처리 속도 갱신
        if process.start_time == None:
            predicted_speed = 0
        else:
            predicted_speed = (process.performed_work + core.performance) / (current_time - process.start_time + 1)
        
        # 남은 일량에 기반한 예측 남은 시간
        remaining_work = max(process.work_load - (process.performed_work + core.performance), 0)
        predicted_remaining_time = remaining_work / (predicted_speed + 1)
        
        # 예측 전체 소요 시간 (현재까지 경과 + 남은 시간)
        predicted_TT = elapsed_time + predicted_remaining_time
        
        # 예측 서비스 시간(= 총 일량 / 평균 속도)
        predicted_service_time = process.work_load / (predicted_speed + 1)
        
        # 정규화된 반환 시간
        predicted_NTT = predicted_TT / (predicted_service_time)

        return 1.0 / (1.0 + math.exp((process.predicted_NTT - predicted_NTT)))
    
    # Cost 계산
    def calculation_cost(self, current_time, core, process):
        watt = self.calculation_Watt(core, process)
        ntt = self.calculation_NTT(current_time, core, process)
        time = self.calculation_Time(current_time, core, process)

        scaled_watt = watt  # 0~1
        scaled_ntt = ntt    # 0~1
        scaled_time = time  # 0~1

        return (self.watt_weight * scaled_watt) + (self.ntt_weight * scaled_ntt) + (self.time_weight * scaled_time)
    
    # 바로 이전 타임라인과 비교해서 컨텍스트 스위칭 조사후 스위칭 개수 반환
    def calculation_context_switching(self, processes):
        if len(self.timeline) > 0:
            time = len(self.timeline) - 1
            pre_timeline = self.timeline[time]

            switching_count = 0
            for i , pre_process in enumerate(pre_timeline):
                # 프로세스가 바뀐다면
                if processes[i].pid != pre_process.pid:
                    switching_count += 1
        else:
            return 0
                
        return switching_count

    # 백트래킹 + 분기 한정 기법
    def backtrack(self, placement, available_processes, result, current_time, cost):
        new_cost = cost

        if 0 < len(placement):
            index = len(placement)-1             # 현재 인덱스
            new_cost = cost + self.calculation_cost(current_time, self.cores[index], placement[index])
            if self.min_cost < new_cost:         # 코스트가 이미 최소보다 크면 분기 종료
                return

        if len(placement) == self.core_num:      # 모든 칸이 채워졌으면
            if all(p.pid is None for p in placement): # 전부 빈칸이면
                if len(available_processes) != 0: # 사용가능한 프로세스가 있다면 빈칸 필요 없음
                    return
            if new_cost < self.min_cost: # 비용이 현재까지의 최소 비용보다 작으면
                self.min_cost = new_cost
            contest_switch = self.calculation_context_switching(placement) # 문맥 전환 우선순위 계산
            result.append(placement.copy() + [new_cost] + [contest_switch]) # 완료된 배치 목록에 추가
            return
        
        # 현재 칸에 빈 프로세스를 넣는 경우
        empty = Process(None, 0, 0)
        placement.append(empty)
        self.backtrack(placement, available_processes, result, current_time, new_cost)
        placement.pop()
        
        # 현재 칸에 사용 가능한 프로세스를 넣는 경우
        for process in available_processes:
            placement.append(process)
            new_available = available_processes.copy()
            new_available.remove(process)
            self.backtrack(placement, new_available, result, current_time, new_cost)
            placement.pop()

    # 확정된 조합을 받아 타임라인에 추가하고 업데이트
    def update_processes(self, current_time ,result):
        self.timeline.append(result)
        for i, process in enumerate(result):
            if process.pid is not None:
                process.update(current_time, self.cores[i].performance) # 코어 상태 업데이트
            self.cores[i].update(process)

    # 스케쥴링 시작
    def run(self):
        current_time = 0
        while (len(self.processes) > 0):
            self.min_cost = float('inf') # 최소 비용 초기화

            result = []
            current_processes = [p for p in self.processes if p.arrival_time <= current_time]

            self.backtrack([], current_processes, result, current_time, 0)

            sorted_result = sorted(result,key=lambda row: (row[-2], row[-1])) # (비용, 스위칭) 순으로 오름차순 정렬
            best = sorted_result[0]
            self.update_processes(current_time, best[0 : self.core_num]) # 프로세스 할당 부분만 전달

            live_processes = []
            for process in self.processes:
                if process.is_end:
                    self.finished_processes.append(process)
                else:
                    live_processes.append(process)
            self.processes = live_processes

            current_time += 1