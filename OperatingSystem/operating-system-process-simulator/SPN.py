class SPN:
    def __init__(self, cores, processes):
        self.core_num = len(cores)
        self.cores = cores          #프로세서 배열
        self.processes = processes  #프로세스 배열
        self.timeline = []          #Index: time   

        self.finished_processes = []

    def check(self, coreList): #현재 코어 중 일하고 있는 코어가 있는지 확인
        for c in coreList:
            if c.running_process:
                return True
        return False

    def run(self):
        time = 0; readyQ = []

        processList = sorted(self.processes, key = lambda p : p.arrival_time) #도착 순 정렬
        coreList = self.cores

        while readyQ or processList or self.check(coreList): #readyQ or process가 빌때까지 or 모든 코어가 일하지 않을 때까지

            cnt = 0
            for p in processList:
                if time >= p.arrival_time: #현재 시간에 도착한 코어들을 readyQ에 넣기
                    readyQ.append(p)
                    cnt += 1

            for _ in range(cnt):
                processList.pop(0) #위 for문에서 remove하면 길이 때문에 오류나서 이렇게 수정, python은 pop()->뒤에값 삭제
            
            readyQ.sort(key = lambda p: p.work_load) #레디큐 work_load순 정렬 (이거 우선순위큐로 바꿔도 됨)
            
            newCores = [] #현재 일하지 않고 있는 코어를 담을 리스트
            for c in coreList:
                if not c.is_running or not c.running_process: 
                    newCores.append(c) #현재 일하지 않고있는 코어
            
            newCores.sort(key = lambda c : c.core_type, reverse=True) #P > E 순 정렬(P먼저 빼주기)

            newReadyQ = []
            for _ in range(len(newCores)): #코어를 프로세스에 배당해주기 위해 일하지 않는 코어개수만큼 readyQ에서 빼주기
                if not readyQ: break 
                newReadyQ.append(readyQ[0])
                readyQ.pop(0)
            
            newReadyQ.sort(key = lambda p : p.work_load, reverse=True) #work_load큰 순 정렬(P코어 먼저 배정해주게)

            for p in newReadyQ: #코어 배정
                if not newCores[0].is_running: #코어가 사용되지 않고 있었다면
                    if newCores[0].core_type == 'P': newCores[0].spend_watt += 0.5 #시동전력 계산
                    else: newCores[0].spend_watt += 0.1 #시동전력 계산
                p.start_time = time #시작 시간
                newCores[0].running_process = p #배정
                newCores[0].is_running = True #코어 사용중
                newCores.pop(0)
            
            for c in newCores: #배정 못받은 코어는 코어 사용 안됨으로 변경
                c.is_running = False
            
            snapshot = [c.running_process if c.running_process is not None else None for c in coreList] #현재 코어에 할당 된 프로세스들 다 타임라인에 집어넣기, 없으면 None
            self.timeline.append(snapshot)

            for c in coreList: #일한 값 계산 중
                if not c.running_process: continue
                p = c.running_process
                p.burst_time += c.performance #일한 시간
                c.spend_watt += c.watt #소비 전력
                if p.burst_time >= p.work_load: #일 다했나?
                    p.end_time = time + 1
                    p.BT  = p.end_time - p.start_time
                    p.TT  = p.end_time - p.arrival_time
                    p.WT  = p.start_time - p.arrival_time
                    p.NTT = p.TT / p.BT
                    self.finished_processes.append(p) #출력을 위한 줄
                    c.running_process = None #코어 일 안하는 중
            
            time += 1 #다음 시간