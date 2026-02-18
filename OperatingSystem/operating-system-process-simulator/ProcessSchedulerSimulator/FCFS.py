class FCFS:
    def __init__(self, cores, processes):
        self.core_num = len(cores)
        #'P'가 앞으로 오게 정렬
        self.cores = sorted(cores, key=lambda c: c.core_type != 'P')
        self.processes = processes # 프로세스 배열
        self.timeline = [] # Index: time
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
    
    def run(self):
        time = 0; readyQ = []
        onetime = [None] * self.core_num; last_onetime = []
        processes = sorted(self.processes, key=lambda p: (-p.arrival_time, p.work_load))

        # 진행
        while (processes or readyQ or not(self.check_end(onetime))):
            last_onetime = onetime[:]
            onetime = [None] * self.core_num

            # 새 프로세스를 readyQ에 삽입
            tmpQ = []
            for i in range(len(processes)-1, -1, -1):
                if (processes[i].arrival_time == time):
                    tmpQ.append(processes[i])
                    processes.pop(i)
                else:
                    break
            readyQ = list(reversed(tmpQ)) + readyQ
            

            # 기존 프로세스 할당
            for i, last_process in enumerate(last_onetime):
                if not(last_process):
                    # last가 None인 경우: 새로운애 넣어야함
                    continue

                elif (last_process.performed_work < last_process.work_load):
                    # 아직 더 돌 수 있는 경우: 기존 프로세스를 계속 유지
                    onetime[i] = last_onetime[i]

            # 새 프로세스 할당
            for i in range(self.core_num):
                if (onetime[i] != None):
                    continue

                new_process = self.safe_pop_idx(readyQ)
                if (new_process == None):
                    continue

                if (new_process.work_load - new_process.performed_work == 1):
                    arrange = 0
                    for j in range(self.p_num, self.core_num):
                        if (onetime[j] == None):
                            onetime[j] = new_process
                            arrange = 1
                            break
                    if not(arrange):
                        onetime[i] = new_process
                else:
                    onetime[i] = new_process

            # 실행
            for i in range(self.core_num):
                if (onetime[i] == None):   continue

                onetime[i].update(time, self.cores[i].performance)
                self.cores[i].update(onetime[i])
                if(onetime[i].is_end):
                    self.finished_processes.append(onetime[i])


            self.timeline.append(onetime[:])
            time += 1