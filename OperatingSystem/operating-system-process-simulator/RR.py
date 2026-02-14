class RR:
    def __init__(self, cores, processes, quantum):
        self.core_num = len(cores)
        #'P'가 앞으로 오게 정렬
        self.cores = sorted(cores, key=lambda c: c.core_type != 'P')
        self.processes = processes  # 프로세스 배열
        self.timeline = []          # Index: time
        self.quantum = quantum
        self.p_num = 0
        for core in cores:
            if (core.core_type == 'P'):
                self.p_num += 1

        self.finished_processes = []


    def safe_pop_idx(self, li):
        return li.pop(-1) if li else None
    
    def check_end(self, li):
        for i in range(len(li)):
            if (li[i] == None): continue
            if (li[i].is_end == False):
                return 0
        return 1
    
    def pid_to_number(self, process):
        return int(process.pid[1:])


    def run(self):
        time = 0; readyQ = []; onetime = [None] * self.core_num; last_onetime = []
        quantum_time = [0] * (len(self.processes)+1)
        processes = sorted(self.processes, key=lambda p: -p.arrival_time)

        # 진행
        while (processes or readyQ or not(self.check_end(onetime))):
            last_onetime = onetime[:]
            onetime = [None] * self.core_num

            # 새 프로세스를 readyQ에 삽입
            for i in range(len(processes)-1, -1, -1):
                if (processes[i].arrival_time == time):
                    readyQ.append(processes[i])
                    processes.pop(i)
                else:
                    break

            # 기존 프로세스 할당
            for i, last_process in enumerate(last_onetime):
                if (last_process == None):
                    # last가 None인 경우: readyQ 다시넣으면 안됨, 새로운애 넣어야함
                    continue

                elif (last_process.performed_work >= last_process.work_load):
                    # 프로세스가 끝난 경우: readyQ 다시 넣으면 안됨, 새로운애 넣어야함
                    continue

                elif (quantum_time[self.pid_to_number(last_process)] < self.quantum):
                    # 아직 더 돌 수 있는 경우 (퀀텀이 남음): onetime에 다시 넣어야함
                    onetime[i] = last_onetime[i]

                else:
                    # 더 돌 수 없는 경우 (퀀텀이 끝남): readyQ에 다시 넣어야함
                    readyQ.append(last_process)
                    quantum_time[self.pid_to_number(last_process)] = 0
                    last_process.last_arrival_time = time

            readyQ = sorted(readyQ, key=lambda p: (-p.last_arrival_time, p.work_load-p.performed_work))
            
            # 새 프로세스 할당 (readyQ의 새로운 애들로 채우기)
            i=0
            while (i<self.core_num):
            # for i in range(self.core_num):
                if (onetime[i] != None):
                    i+=1
                    continue

                new_process = self.safe_pop_idx(readyQ)
                if (new_process == None):
                    i+=1
                    continue

                if (new_process.work_load - new_process.performed_work == 1):
                    arrange = 0
                    for j in range(self.p_num, self.core_num):
                        if (onetime[j] == None):
                            onetime[j] = new_process
                            arrange = 1
                            i-=1
                            break
                    if not(arrange):
                        onetime[i] = new_process
                else:
                    onetime[i] = new_process
                
                i+=1

            # 실행
            for i in range(self.core_num):
                if (onetime[i] == None):   continue

                onetime[i].update(time, self.cores[i].performance)
                self.cores[i].update(onetime[i])
                quantum_time[self.pid_to_number(onetime[i])] += 1
                if(onetime[i].is_end):
                    self.finished_processes.append(onetime[i])


            self.timeline.append(onetime[:])
            time += 1