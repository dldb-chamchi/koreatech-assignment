class SRTN:
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

        eCore = 0; pCore = 0
        for c in coreList:
            if c.core_type == 'P': pCore += 1
            else: eCore += 1

        while readyQ or processList or self.check(coreList): #readyQ or process가 빌때까지 or 모든 코어가 일하지 않을 때까지
        
            cnt = 0
            for p in processList:
                if time >= p.arrival_time:
                    readyQ.append(p)
                    cnt += 1

            for _ in range(cnt):
                processList.pop(0) #위 for문에서 remove하면 길이 때문에 오류나서 이렇게 수정, python은 pop()->뒤에값 삭제

            newCores = []           #현재 안돌고 있는 코어
            whichCoreAssingedQ = [] #선점을 위해 readyQ에 있는 애들 중 남은 시간이 빠른애들 순으로 넣어주기(new core 개수 만큼)
            eCoreAssingedQ = []     #선점하려는데 만약 P코어가 비어있고 E코어에 배정 받은 애가 있다면 readyQ에 있는 애랑 같이 경쟁하기
            nowPcore = 0            #현재 P코어가 비었는지 안비었는지 개수 판단
            for c in coreList:
                if not c.is_running or not c.running_process:
                    if c.core_type == 'P': nowPcore += 1 #P코어 비었어요
                    newCores.append(c) #현재 일하지 않고있는 코어
                elif c.running_process and c.core_type == 'E': #E코어와 readyQ에 있는애 경쟁하기 위해
                    eCoreAssingedQ.append(c.running_process)
            
            #남은 시간 정렬
            readyQ.sort(key = lambda p : p.work_load-p.burst_time) #코어 배정 안된 프로세스가 배정된 프로세스보다 남은시간이 작은지를 봐야함
            
            #코어 배정
            if newCores: #한 코어라도 일하지 않고 있을 때(보통 초반에)
                #이 일하고 있지 않은 애가 P라면 E에 돌고있던애도 같이 경쟁해줘야함 
            
                for i in range(len(newCores)):
                    if not readyQ:
                        break
                    whichCoreAssingedQ.append(readyQ[0]) #코어 배정을 위한 큐로 이동
                    readyQ.pop(0)

                whichCoreAssingedQ.sort(key = lambda p : p.work_load-p.burst_time, reverse=True) #남은 시간 큰 순 정렬, work_load 큰 애를 P에 배정해주기 위해
                eCoreAssingedQ.sort(key = lambda p : p.work_load-p.burst_time, reverse=True)

                length = min(len(eCoreAssingedQ), len(whichCoreAssingedQ)) if whichCoreAssingedQ else len(eCoreAssingedQ)

                #which len이 0일경우 -> E 그냥 P 주면됨
                for c in coreList: #이건 지금 프로세스에 어떤 코어가 배정 되었는지 알 수 없기 때문에 있는 for문
                    for i in range(length):
                        if c.running_process == eCoreAssingedQ[i]: #코어에 돌고 있는 애가 아까 위에서 본 E 코어에 돌고 있던 걔 맞나?
                            if not whichCoreAssingedQ and nowPcore > 0 and eCoreAssingedQ[i].work_load- eCoreAssingedQ[i].burst_time > 1:
                                #P 줄지 판단, 만약 which가 비어있다면 걍 주면됨(대신 빈코어가 P코어여야하고 남은 시간이 적어도 2이상은 되야됨)
                                whichCoreAssingedQ.append(c.running_process) #코어 배정 큐에 넣어줌
                                c.running_process = None #돌고 있는 코어는 None으로
                                nowPcore -= 1 #다른 P를 차지하려는 아이가 헷갈리지 않도록
                                break
                            elif not whichCoreAssingedQ: #위에 if에 걸리지 않으면 E에 있는게 낫다는 뜻
                                break
                            elif i >= len(eCoreAssingedQ) or i >= len(whichCoreAssingedQ):
                                break
                            
                            #그럼 현재 E에 할당된 프로세스랑 readyQ에서 들어오는 프로세스랑 누가 P를 가질지 대결해보자
                            p1 = eCoreAssingedQ[i]
                            p2 = whichCoreAssingedQ[i]

                            if p1.work_load - p1.burst_time > p2.work_load - p2.burst_time and nowPcore > 0:
                                #막상 P는 남은 시간이 큰 애한테 줘야하기 때문에 이런식으로 (P가 실제 비어있는지도 확인)
                                whichCoreAssingedQ.append(p1)
                                c.running_process = None
                                nowPcore -= 1 #다른 P코어를 차지하려는 아이가 헷갈리지 않도록
                                newCores.append(c)
                            else:
                                break
                            
                #남은 시간 큰 순 정렬(이제 진짜 배정할 프로세스들)
                whichCoreAssingedQ.sort(key = lambda p : p.work_load-p.burst_time, reverse=True) 
                newCores.sort(key = lambda c : c.core_type, reverse=True) #P > E 순 정렬(P먼저 빼주기)

                for p in whichCoreAssingedQ: #실제 코어 배정
                    if not newCores[0].is_running:
                        if newCores[0] .core_type == 'P': newCores[0].spend_watt += 0.5 #시동 전력
                        else: newCores[0].spend_watt += 0.1
                    if p.start_time == None : p.start_time = time
                    newCores[0].running_process = p
                    newCores[0].is_running = True
                    newCores.pop(0)

           
            assignedCoresNum = 0 #모든 코어가 일하고 있는지 확인
            for c in coreList:
                if c.running_process: assignedCoresNum += 1
            
            if assignedCoresNum == self.core_num: #모든 코어가 일하고 있다면 readyQ의 프로세스들과 교환할게 있는지 봐주어야함
            
                #원본 정렬 말고 복사본생성(원본 정렬은 나중에 출력 때 이상해짐)
                sortedCores = sorted(coreList, key=lambda c: (c.running_process.work_load - c.running_process.burst_time, c.core_id), reverse=True)
                #일부러 남은 시간 역순 정렬 해서 readyQ에 가장 남은 시간이 빠른애와 현재 돌고 있는 프로세스의 가장 남은 시간이 긴애를 교환하도록
                
                #현재 돌고있는 프로세스랑 readyQ랑 비교, newCores가 0일떄(모든 코어가 일하는 중)    
                for i in range(len(sortedCores)):
                    if not readyQ: break
                    p1 = sortedCores[i].running_process
                    p2 = readyQ[0]
                    if p1.work_load - p1.burst_time > p2.work_load - p2.burst_time: #만약 현재 돌고 있는 프로세스 남은시간이 readyQ 프로세스보다 크면 교환
                        sortedCores[i].running_process = p2
                        if not sortedCores[i].running_process.start_time:
                            sortedCores[i].running_process.start_time = time
                        readyQ.pop(0)
                        readyQ.append(p1)
                    else: #한번이라도 이곳에 오면 이제 교환 필요 없음(현재 돌고 있는 애들 중 가장 남은 시간이 긴 애 보다도 남은 시간이 큰애가 readyQ에 있다는 뜻임)
                        break

            for c in newCores: #배정 못받은 코어는 None으로
                c.is_running = False
            
            snapshot = [c.running_process if c.running_process is not None else None for c in self.cores]
            self.timeline.append(snapshot)

            for c in coreList: #일한 값 계산 중
                if not c.running_process: continue
                p = c.running_process
                p.burst_time += c.performance
                p.performed_work += 1
                c.spend_watt += c.watt
                if p.burst_time >= p.work_load:
                    p.end_time = time + 1
                    p.BT  = p.performed_work
                    p.TT  = p.end_time - p.arrival_time
                    p.WT  = p.TT - p.BT #TT-BT
                    p.NTT = p.TT / p.BT
                    self.finished_processes.append(p)
                    c.running_process = None
            
            time += 1