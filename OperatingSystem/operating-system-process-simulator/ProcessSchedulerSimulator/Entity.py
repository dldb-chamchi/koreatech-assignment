class Process:
    def __init__(self, pid, arrival_time, work_load):
        self.pid = pid                          # 프로세스 ID
        self.arrival_time = arrival_time        # 도착 시간
        self.work_load = work_load              # 총 실행(일량) 양 = 상수값
        self.last_arrival_time = arrival_time   # 마지막 도착 시간
        self.ratio = 0                          # HRRN의 aging

        self.burst_time = 0                     # 총 실행 시간
        self.performed_work = 0                 # 실행된 (일량) 양
        self.is_started = False                 # 프로세스 시작 판정
        self.start_time = None                  # 처음 실행 시작 시각
        self.is_end = False                     # 프로세스 종료 판정
        self.end_time = None                    # 종료 시각

        # 예측값 (myAlgo에서 사용)
        self.predicted_remaining_time = 0       # 남은 예상 소요 시간
        self.predicted_TT = 0                   # 전체 예상 소요 시간
        self.predicted_service_time = 0         # 예측 서비스 시간(= 총 일량 / 평균 속도)
        self.predicted_NTT = 1
        self.predicted_speed = 0

        # 최종값
        self.BT = None
        self.WT = None
        self.TT = None
        self.NTT = None

    def update(self, current_time, performed_work):
        # 첫 프로세스 작업 시작이라면
        if self.start_time == None and self.is_started == False:
            self.start_time = current_time
            self.is_started = True
        # 수행된 일 추가
        self.performed_work += performed_work

        # 지금까지 걸린 시간
        elapsed_time = current_time - self.arrival_time
        
        # 평균 처리 속도 갱신
        self.predicted_speed = self.performed_work / (current_time - self.start_time + 1)
        
        # 남은 일량에 기반한 예측 남은 시간
        remaining_work = max(self.work_load - self.performed_work, 0)
        self.predicted_remaining_time = remaining_work / (self.predicted_speed + 1)
        
        # 예측 전체 소요 시간 (현재까지 경과 + 남은 시간)
        self.predicted_TT = elapsed_time + self.predicted_remaining_time
        
        # 예측 서비스 시간(= 총 일량 / 평균 속도)
        self.predicted_service_time = self.work_load / (self.predicted_speed + 1)
        
        # 정규화된 반환 시간
        self.predicted_NTT = self.predicted_TT / (self.predicted_service_time)

        self.burst_time += 1
        # 일 수행 다 했다면
        if self.performed_work >= self.work_load:
            self.is_end = True
            self.end_time = current_time+1
            self.BT = self.burst_time
            self.TT = self.end_time - self.arrival_time
            self.WT = self.start_time - self.arrival_time
            self.NTT = self.TT / (self.BT)

    def update_trash(self, current_time, performed_work):
        E = 10**-8

        # 첫 프로세스 작업 시작이라면
        if self.start_time == 0 and self.is_started == False:
            self.start_time = current_time
            self.is_started = True
        
        # 작업량 누적
        self.performed_work += performed_work
        self.predicted_speed = (self.performed_work) / (current_time - self.start_time + 1)
        self.estimated_time_remaining = (       # 남은 예상 소요 시간
            (self.work_load - self.performed_work) / (self.predicted_speed + E) )
        self.estimated_overall_time = (         # 전체 예상 소요 시간
        (self.work_load) / (self.predicted_speed + E) )
        self.predicted_NTT = (                  # 추정 NTT
            (self.start_time + self.estimated_overall_time) / self.work_load
        )

        
        # 일 수행 다 했다면
        if self.performed_work >= self.work_load:
            self.is_end = True
            self.end_time = current_time
            self.BT = self.burst_time
            self.TT = self.end_time - self.arrival_time
            self.WT = self.start_time - self.arrival_time
            self.NTT = self.TT / (self.BT+E)
        else:
            # 일단 할당되면 1초 일함
            self.burst_time += 1


class Core:
    def __init__(self, core_id, core_type):
        core_performance = {'P': 2, 'E': 1}
        core_watt = {'P': 3, 'E': 1}
        core_start_watt = {'P': 0.5, 'E': 0.1}

        self.core_id = core_id
        self.core_type = core_type

        self.performance = core_performance.get(core_type)
        self.watt = core_watt.get(core_type)
        self.start_watt = core_start_watt.get(core_type)

        self.is_running = False
        self.running_process = None     # 할당된 프로세스

        self.spend_watt = 0

    def update(self, process):
        self.running_process = process
        if process.pid is None: # 프로세스가 빈 프로세스면
            self.is_running = False
        else:
            if self.is_running:
                self.spend_watt += self.watt
            else:
                self.spend_watt += self.watt + self.start_watt
            self.is_running = True