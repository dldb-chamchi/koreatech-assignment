import tkinter
from tkinter import messagebox, ttk
import random

from entity import Process, Core
from FCFS import FCFS
from RR import RR
from SPN import SPN
# from SRTN_seong import SRTN
from SRTN_ari import SRTN
from HRRN import HRRN
from myAlgorithm import Algorithm

def add_process():
    if (len(process_tree.get_children())>=15):
        messagebox.showerror("Error", "프로세스 개수는 최대 15입니다.")
        return
    try:
        arrival_time = int(arrival_time_entry.get())
        work_load = int(work_load_entry.get())
    except ValueError:
        messagebox.showerror("Error", "숫자를 입력해야 합니다.")
        return
    if (arrival_time<0 or work_load<0):
        messagebox.showerror("Error", "음수를 입력할 수 없습니다.")
        return
    pid = f"P{len(process_tree.get_children())+1}"
    process_tree.insert("", tkinter.END, values=(pid, arrival_time, work_load))
    arrival_time_entry.delete(0, tkinter.END)
    work_load_entry.delete(0, tkinter.END)

def add_random_process():
    if (len(process_tree.get_children())>=15):
        messagebox.showerror("Error", "프로세스는 15개 이상 만들 수 없습니다.")
        return
    pid = f"P{len(process_tree.get_children())+1}"
    arrival_time = random.randint(0, 15)
    work_load = random.randint(1, 15)
    process_tree.insert("", tkinter.END, values=(pid, arrival_time, work_load))

def add_preset_process():
    pcore_entry.delete(0, tkinter.END)
    ecore_entry.delete(0, tkinter.END)
    remove_all()

    core_presets = (
        ("2", "2"),
        ("1", "1"),
        ("1", "1"),
        ("1", "1"),
        ("1", "1"),
        ("1", "1"),
        ("1", "1"),
    )
    process_presets = (
        ((0, 6), (0, 3), (0, 8), (0, 2), (0, 10), (1, 4), (1, 9), (2, 5), (2, 12), (3, 7), (3, 11), (3, 1), (4, 14), (4, 6), (5, 13)),
        ((0, 3), (0, 4), (1, 8), (2, 5)),
        ((0, 6), (1, 1), (2, 8)),
        ((0, 9), (0, 8), (0, 1)),
        ((0, 3), (0, 4), (0, 4), (2, 1)),
        ((0, 8), (0, 3), (1, 2))
    )
    preset_idx = int(preset_selection.get())-1

    pcore_entry.insert(0, core_presets[preset_idx][0])
    ecore_entry.insert(0, core_presets[preset_idx][1])
    for i in range(len(process_presets[preset_idx])):
        process_tree.insert("", tkinter.END, values=(f"P{i+1}", process_presets[preset_idx][i][0], process_presets[preset_idx][i][1]))
    if (preset_idx == 0):
        watt_weight_entry.insert(0, "0.1")
        ntt_weight_entry.insert(0, "1")
        time_weight_entry.insert(0, "3")
        
def remove_selected():
    for item in process_tree.selection():
        process_tree.delete(item)

    for i, item in enumerate(process_tree.get_children()):
        _, arrival_time, work_load = process_tree.item(item)["values"]
        pid = f"P{i+1}"
        process_tree.item(item, values=(pid, arrival_time, work_load))

def remove_all():
    for item in process_tree.get_children():
        process_tree.delete(item)

def sched_result():
    try:
        pcore = int(pcore_entry.get())
        ecore = int(ecore_entry.get())
    except ValueError:
        messagebox.showerror("Error", "숫자를 입력해야 합니다.")
        return None, None
    
    core_num = pcore + ecore
    if (pcore < 0 or ecore < 0) or (core_num > 4):
        messagebox.showerror("Error", "코어 개수는 0 이상 4 이하만 가능합니다.")
        return None, None
    
    cores = [Core(i, 'P') for i in range(pcore)] + [Core(i, 'E') for i in range(ecore)]
    processes = [
        Process(
            process_tree.item(item)["values"][0],
            int(process_tree.item(item)["values"][1]),
            int(process_tree.item(item)["values"][2])
        ) for item in process_tree.get_children()
    ]
    if (len(processes) == 0):
        messagebox.showerror("Error", "등록된 프로세스가 없습니다.")
        return None, None

    algorithm = algorithm_selection.get()
    if (algorithm == "FCFS"):
        sched_res = FCFS(cores, processes)
    elif (algorithm == "RR"):
        try:
            tq = int(timeq_entry.get())
        except ValueError:
            messagebox.showerror("Error", "Time Quantum은 숫자여야 합니다.")
            return None, None
        if (tq < 0):
            messagebox.showerror("Error", "Time Quantum은 음수가 될 수 없습니다.")
            return None, None
        sched_res = RR(cores, processes, tq)
    elif (algorithm == "SPN"):
        sched_res = SPN(cores, processes)
    elif (algorithm == "SRTN"):
        sched_res = SRTN(cores, processes)
    elif (algorithm == "HRRN"):
        sched_res = HRRN(cores, processes)
    else:
        try:
            ww = float(watt_weight_entry.get())
            nw = float(ntt_weight_entry.get())
            tw = float(time_weight_entry.get())
        except ValueError:
            messagebox.showerror("Error", "가중치는 숫자여야 합니다.")
            return None, None
        if (ww<0 or nw<0 or tw<0):
            messagebox.showerror("Error", "가중치는 음수가 될 수 없습니다.")
            return None, None
        sched_res = Algorithm(cores, processes, ww, nw, tw)

    sched_res.run()
    return algorithm, sched_res

def result_GUI():
    selected_algo, sched_res = sched_result()

    if (sched_res == None):
        return

    max_time = len(sched_res.timeline)
    
    schedules = []
    for i in range(sched_res.core_num):
        tmp = []
        time = 0
        pre_pid = sched_res.timeline[0][i].pid if (sched_res.timeline[0][i] != None) else ""
        for t in range(max_time):
            cur_pid = sched_res.timeline[t][i].pid if (sched_res.timeline[t][i] != None) else ""
    
            if (cur_pid != pre_pid):
                tmp.append((pre_pid, time))
                time = 1
            else:
                time += 1
            pre_pid = cur_pid
        tmp.append((cur_pid, time))
        schedules.append(tmp)
    
    result = tkinter.Toplevel()
    result.title("Result")
    result.geometry("800x500")
    result.columnconfigure(0, weight=1)
    result.rowconfigure(1, weight=1)
    result.rowconfigure(2, weight=1)

    # result title
    title_frame = tkinter.Frame(result)
    title_frame.grid(row=0, column=0, sticky="ew", padx=10, pady=5)
    title_frame.columnconfigure(0, weight=1)
    tkinter.Label(title_frame, text="Result", font=("Segoe UI", 14, "bold")).grid(row=0, column=0, sticky="w", padx=10)
    tkinter.Button(title_frame, text="종료", command=result.destroy).grid(row=0, column=1, sticky="e", padx=10)

    # gantt
    gantt_frame = tkinter.LabelFrame(result, text="Gantt Chart")
    gantt_frame.grid(row=1, column=0, sticky="nsew", padx=10, pady=5)
    gantt_frame.columnconfigure(0, weight=1)
    gantt_frame.rowconfigure(0, weight=1)

    header_h = 20
    row_h = 40
    scale = 40
    total_h = header_h + sched_res.core_num*row_h

    gantt_area = tkinter.Canvas(gantt_frame, bg="white")
    gantt_area.grid(row=0, column=0, sticky="nsew")
    gantt_area.config(height=total_h)
    
    # time dash
    for t in range(max_time+1):
        x = 100 + t*scale
        gantt_area.create_line(x, header_h, x, total_h, fill="lightgray", dash=(2,2))
        gantt_area.create_text(x, header_h - 2, text=str(t), anchor="s")

    # gantt draw
    colors = {}
    color_code = ("#ffa69e", "#ffbf69", "#fbf8cc", "#fde4cf", "#ffcfd2", "#f1c0e8", "#cfbaf0", "#a3c4f3", "#90dbf4", "#e9edc9", "#98f5e1", "#b9fbc0", "#c8d5b9", "#68b0ab", "#dad7cd")
    for i in range(len(sched_res.finished_processes)):
        colors[sched_res.finished_processes[i].pid] = color_code[i]

    for i in range(sched_res.core_num):
        y = header_h + i*row_h
        gantt_area.create_text(10, y + row_h/2, text=f"Core {i} ({sched_res.cores[i].core_type}-core)",anchor="w")
        cur = 0
        for pid, t in schedules[i]:
            if (pid == '' or pid == None):
                cur += t
                continue
            x0 = 100 + cur*scale
            x1 = 100 + (cur + t)*scale
            gantt_area.create_rectangle(x0, y+5, x1, y+row_h-5, fill=colors[pid], outline="black")
            gantt_area.create_text((x0+x1)/2, y + row_h/2, text=pid)
            cur += t

    gantt_scrollbar = ttk.Scrollbar(gantt_frame, orient="horizontal", command=gantt_area.xview)
    gantt_scrollbar.grid(row=1, column=0, sticky="ew")
    gantt_area.configure(xscrollcommand=gantt_scrollbar.set)
    gantt_area.configure(scrollregion=(0, 0, max_time*scale+125, total_h))

    # result table
    result_table_frame = tkinter.Frame(result, bd=1, relief="groove")
    result_table_frame.grid(row=2, column=0, sticky="nsew", padx=10, pady=5)
    result_table_frame.columnconfigure(0, weight=1)
    result_table_frame.rowconfigure(0, weight=1)

    cols = ("Process ID", "Workload", "AT", "BT", "WT", "TT", "NTT")
    result_tree = ttk.Treeview(result_table_frame, columns=cols, show="headings", height=6)
    for c in cols:
        result_tree.heading(c, text=c)
        result_tree.column(c, width=80, anchor="center")
    result_tree.grid(row=0, column=0, sticky="nsew")

    for process in sched_res.finished_processes:
        result_tree.insert("", tkinter.END, values=(
            process.pid,
            process.work_load,
            process.arrival_time,
            process.BT,
            process.WT,
            process.TT,
            f"{process.NTT:.2f}"
        ))

    table_scrollbar = ttk.Scrollbar(result_table_frame, orient="vertical", command=result_tree.yview)
    table_scrollbar.grid(row=0, column=1, sticky="ns")
    result_tree.configure(yscrollcommand=table_scrollbar.set)
    
    # stats
    stats_frame = tkinter.Frame(result, bd=1, relief="groove")
    stats_frame.grid(row=5, column=0, sticky="ew", padx=10, pady=5)
    for i in range(5):
        stats_frame.columnconfigure(i, weight=1)

    total_p_watt = 0
    total_e_watt = 0
    for core in sched_res.cores:
        if core.core_type == 'P':
            total_p_watt += core.spend_watt
        elif core.core_type == 'E':
            total_e_watt += core.spend_watt

    tkinter.Label(stats_frame, text=f"스케줄링 기법: {selected_algo}").grid(row=0, column=0, padx=10)
    tkinter.Label(stats_frame, text=f"처리 작업 개수: {len(sched_res.finished_processes)}").grid(row=0, column=1, padx=10)
    tkinter.Label(stats_frame, text=f"전체 수행 시간(s): {max_time}").grid(row=0, column=2, padx=10)
    tkinter.Label(stats_frame, text=f"P core 소비 전력(W): {total_p_watt:.1f}").grid(row=0, column=3, padx=10)
    tkinter.Label(stats_frame, text=f"E core 소비 전력(W): {total_e_watt:.1f}").grid(row=0, column=4, padx=10)

root = tkinter.Tk()

root.title("Process Scheduling Simulator")
root.geometry("900x570")
root.columnconfigure(1, weight=1)

# title
title_frame = tkinter.Frame(root)
title_frame.grid(row=0, column=0, columnspan=2, sticky="ew", padx=10, pady=5)
title_frame.columnconfigure(0, weight=1)
# title: 제목
title_label = tkinter.Label(title_frame, text="Process Scheduling Simulator", font=("Segoe UI", 16, "bold"))
title_label.grid(row=0, column=0, sticky="w", padx=10)
# title: 시작 버튼
start_button = tkinter.Button(title_frame, text="Start", font=("Segoe UI", 12, "bold"), command=result_GUI)
start_button.grid(row=0, column=1, sticky="e", padx=10, pady=5)

# setting
setting_frame = tkinter.Frame(root, bd=1, relief="groove")
setting_frame.grid(row=1, column=0, sticky="nsew", padx=10, pady=10)
setting_frame.columnconfigure(0, weight=1)

# setting: 코어
core_frame = tkinter.LabelFrame(setting_frame, text="코어 설정")
core_frame.grid(row=0, column=0, sticky="ew", padx=5, pady=5)
core_frame.columnconfigure(0, weight=1)

tkinter.Label(core_frame, text="P core").grid(row=0, column=0, padx=5, pady=5, sticky="w")
pcore_entry = tkinter.Entry(core_frame, width=10)
pcore_entry.grid(row=0, column=1, padx=5, pady=5, sticky="e")
pcore_entry.insert(0, "2")
tkinter.Label(core_frame, text="E core").grid(row=1, column=0, padx=5, pady=5, sticky="w")
ecore_entry = tkinter.Entry(core_frame, width=10)
ecore_entry.grid(row=1, column=1, padx=5, pady=5, sticky="e")
ecore_entry.insert(0, "2")

# setting: 스케줄링 알고리즘
sched_frame = tkinter.LabelFrame(setting_frame, text="스케줄링 기법")
sched_frame.grid(row=1, column=0, sticky="ew", padx=5, pady=5)
sched_frame.columnconfigure(1, weight=1)

schedule_options = ["FCFS", "RR", "SPN", "SRTN", "HRRN", "own algorithm"]
tkinter.Label(sched_frame, text="Scheduling").grid(row=0, column=0, padx=5, pady=5, sticky="w")
algorithm_selection = ttk.Combobox(sched_frame, values=schedule_options, state="readonly", width=12)
algorithm_selection.grid(row=0, column=1, padx=5, pady=5, sticky="e")
algorithm_selection.current(0)

tkinter.Label(sched_frame, text="Time quantum(RR)").grid(row=1, column=0, padx=5, pady=5, sticky="w")
timeq_entry = tkinter.Entry(sched_frame, width=10)
timeq_entry.grid(row=1, column=1, padx=5, pady=5, sticky="e")
# timeq_entry.insert(0, "2")

tkinter.Label(sched_frame, text="Watt Weight(own algorithm)").grid(row=2, column=0, padx=5, pady=5, sticky="w")
watt_weight_entry = tkinter.Entry(sched_frame, width=10)
watt_weight_entry.grid(row=2, column=1, padx=5, pady=5, sticky="e")
# watt_weight_entry.insert(0, "0")

tkinter.Label(sched_frame, text="NTT Weight(own algorithm)").grid(row=3, column=0, padx=5, pady=5, sticky="w")
ntt_weight_entry = tkinter.Entry(sched_frame, width=10)
ntt_weight_entry.grid(row=3, column=1, padx=5, pady=5, sticky="e")
# ntt_weight_entry.insert(0, "1")

tkinter.Label(sched_frame, text="Time Weight(own algorithm)").grid(row=4, column=0, padx=5, pady=5, sticky="w")
time_weight_entry = tkinter.Entry(sched_frame, width=10)
time_weight_entry.grid(row=4, column=1, padx=5, pady=5, sticky="e")
# time_weight_entry.insert(0, "1")

# setting: add process
add_frame = tkinter.LabelFrame(setting_frame, text="프로세스 추가")
add_frame.grid(row=2, column=0, sticky="ew", padx=5, pady=5)
add_frame.columnconfigure(1, weight=1)

tkinter.Label(add_frame, text="Arrival Time").grid(row=0, column=0, padx=5, pady=2, sticky="w")
arrival_time_entry = tkinter.Entry(add_frame, width=10)
arrival_time_entry.grid(row=0, column=1, padx=5, pady=2, sticky="e")

tkinter.Label(add_frame, text="Workload").grid(row=1, column=0, padx=5, pady=2, sticky="w")
work_load_entry = tkinter.Entry(add_frame, width=10)
work_load_entry.grid(row=1, column=1, padx=5, pady=2, sticky="e")

add_button = tkinter.Button(add_frame, text="추가", command=add_process)
add_button.grid(row=2, column=1, padx=5, pady=5, sticky="e")
random_button = tkinter.Button(add_frame, text="랜덤 추가", command=add_random_process)
random_button.grid(row=2, column=1, padx=45, pady=5, sticky="e")

# setting: preset
preset_frame = tkinter.LabelFrame(setting_frame, text="프로세스 프리셋 사용")
preset_frame.grid(row=3, column=0, sticky="ew", padx=5, pady=5)
preset_frame.columnconfigure(1, weight=1)

preset_options = ["1", "2", "3", "4", "5", "6"]
tkinter.Label(preset_frame, text="Preset").grid(row=0, column=0, padx=5, pady=5, sticky="w")
preset_selection = ttk.Combobox(preset_frame, values=preset_options, state="readonly", width=12)
preset_selection.grid(row=0, column=1, padx=5, pady=5, sticky="e")
preset_selection.current(0)
preset_button = tkinter.Button(preset_frame, text="프리셋 선택", command=add_preset_process)
preset_button.grid(row=1, column=1, padx=5, pady=5, sticky="e")

# setting: processes
processes_frame = tkinter.LabelFrame(root, bd=1, relief="groove")
processes_frame.grid(row=1, column=1, sticky="nsew", padx=10, pady=10)
processes_frame.columnconfigure(0, weight=1)
processes_frame.rowconfigure(0, weight=1)

process_columns = ("Process ID", "Arrival Time", "Workload")
process_tree = ttk.Treeview(processes_frame, columns=process_columns, show="headings", height=10)
for col in process_columns:
    process_tree.heading(col, text=col)
    process_tree.column(col, width=100, anchor=tkinter.CENTER)
process_tree.grid(row=0, column=0, columnspan=2, sticky="nsew", padx=5, pady=5)

# setting: processes - 프로세스 삭제
remove_button = tkinter.Button(processes_frame, text="선택된 프로세스 삭제", command=remove_selected)
remove_button.grid(row=1, column=0, padx=125, pady=5, sticky="e")
remove_all_button = tkinter.Button(processes_frame, text="프로세스 전체 삭제", command=remove_all)
remove_all_button.grid(row=1, column=0, padx=5, pady=5, sticky="e")

root.mainloop()