import pygame
from random import *
import time
# 레벨에 맞게 설정
def setup(level):

    global display_time
    display_time = 5 - (level // 3)
    display_time = max(display_time, 1)
    # 얼마나 많은 숫자를 보여줄 것인가?
    number_count = (level // 3) + 5
    number_count = min(number_count, 20) # 만약 20 을 초과하면 20 으로 처리

    # 실제 화면에 grid 형태로 숫자를 랜덤으로 배치
    shuffle_grid(number_count)

# 숫자 섞기 (이 프로젝트에서 가장 중요)
def shuffle_grid(number_count):
    rows = 5
    columns = 9

    cell_size = 130 # 각 Grid cell 별 가로, 세로 크기
    button_size = 110 # Grid cell 내에 실제로 그려질 버튼 크기
    screen_left_margin = 55 # 전체 스크린 왼쪽 여백
    screen_top_margin = 20 # 전체 스크린 위쪽 여백

    # [[0, 0, 0, 0, 0, 0, 0, 5, 0],
    #  [0, 0, 0, 0, 0, 4, 0, 0, 0],
    #  [0, 0, 1, 0, 0, 0, 2, 0, 0],
    #  [0, 0, 0, 0, 3, 0, 0, 0, 0],
    #  [0, 0, 0, 0, 0, 0, 0, 0, 0]]
    grid = [[0 for col in range(columns)] for row in range(rows)] # 5 x 9

    number = 1 # 시작 숫자 1부터 number_count 까지, 만약 5라면 5까지 숫자를 랜덤으로 배치하기
    while number <= number_count:
        row_idx = randrange(0, rows) # 0, 1, 2, 3, 4 중에서 랜덤으로 뽑기
        col_idx = randrange(1, columns) # 0 ~ 8 중에서 랜덤으로 뽑기

        if grid[row_idx][col_idx] == 0:
            grid[row_idx][col_idx] = number # 숫자 지정
            number += 1

            # 현재 grid cell 위치 기준으로 x, y 위치를 구함
            center_x = screen_left_margin + (col_idx * cell_size) + (cell_size / 2)
            center_y = screen_top_margin + (row_idx * cell_size) + (cell_size / 2)

            # 숫자 버튼 만들기
            button = pygame.Rect(0, 0, button_size, button_size)
            button.center = (center_x, center_y)

            number_buttons.append(button)

    # 배치된 랜덤 숫자 확인
    print(grid)

# 시작 화면 보여주기
def display_start_screen():
    global curr_level
    pygame.draw.circle(screen, WHITE, start_button.center, 60, 5)
    screen.blit(text, (85, screen_height - 135))
    screen.blit(ms_text, (450, 100))
    screen.blit(ms_text2, (385, 270))
    screen.blit(ID_text_screen, (530, 600))

    if curr_level == 1:
        screen.blit(text, (85, screen_height - 135))
    else:
        msg = game_font.render(f"{curr_level}", True, WHITE)
        msg_rect = msg.get_rect(center=start_button.center)
        screen.blit(msg, msg_rect)
    




# 게임 화면 보여주기
def display_game_screen():
    global hidden, elapsed_time, sc

    if not hidden:
        elapsed_time = (pygame.time.get_ticks() - start_ticks) / 1000 # ms -> sec
        if elapsed_time > display_time:
            hidden = True
        

    for idx, rect in enumerate(number_buttons, start=1):
        pygame.draw.rect(screen, GRAY, rect)

        screen.blit(inform, (30, 5))
        screen.blit(ID_text, (10, 60))
        screen.blit(time, (15, 130))
        screen.blit(timer_text, (30, 180))
        screen.blit(cur_d, (15, 250))
        screen.blit(cur_d_te, (35, 300))
        # 실제 숫자 텍스트
        cell_text = game_font.render(str(idx), True, WHITE)
        text_rect = cell_text.get_rect(center=rect.center)
        screen.blit(cell_text, text_rect)
        pygame.draw.line(screen, WHITE, [150, 10], [150, 707], 4) #inform 구분선
        pygame.draw.line(screen, WHITE, [0, 45], [150, 45], 4)
    
        if hidden: # 숨김 처리
            # 버튼 사각형 그리기
            pygame.draw.rect(screen, WHITE, rect)
        else:
            # 실제 숫자 텍스트
            cell_text = game_font.render(str(idx), True, WHITE)
            text_rect = cell_text.get_rect(center=rect.center)
            screen.blit(cell_text, text_rect)

    return elapsed_time

    


# pos 에 해당하는 버튼 확인
def check_buttons(pos):
    global start, sc, start_ticks

    if start: # 게임이 시작했으면?
        check_number_buttons(pos)

    elif start_button.collidepoint(pos):
        start = True
        start_ticks = pygame.time.get_ticks()

    if start_button.collidepoint(pos):
        number_buttons.clear()
        setup(1)
        sc = False
        start = True


def check_number_buttons(pos):
    global start, hidden, curr_level, sc

    for button in number_buttons:
        if button.collidepoint(pos):
            if button == number_buttons[0]: # 올바른 숫자 클릭
                # print("Correct")  
                del number_buttons[0]
                if not hidden:
                    hidden = True            
            else: # 잘못된 숫자 클릭
                sc = True
                start = False
                display_score_screen()
            break

    if len(number_buttons) == 0:
        start = False
        hidden = False
        curr_level += 1
        setup(curr_level)


def display_score_screen():
    global hidden
    screen.blit(best, (350, 100))    
    screen.blit(cur, (500, 300))
    pygame.draw.circle(screen, WHITE, start_button.center, 60, 5)
    screen.blit(restart,(70, screen_height - 135))
    hidden = False





        
# 초기화
pygame.init()
screen_width = 1280 # 가로 크기
screen_height = 720 # 세로 크기
screen = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("Memory Game")
game_font = pygame.font.Font(None, 120) # 폰트 정의

# 시작 버튼
start_button = pygame.Rect(0, 0, 120, 120)
start_button.center = (120, screen_height - 120)


#색깔
BLACK = (0, 0, 0) # RGB 
WHITE = (255, 255, 255)
GRAY = (50, 50, 50)


number_buttons = []
curr_level = 1

#시간 구하기
display_time = None # 숫자를 보여주는 시간
start_ticks = None # 시간 계산 (현재 시간 정보를 저장)

start = False
sc = False
hidden = False
setup(curr_level)

#폰트
ID_font = pygame.font.SysFont("arial", 30, True, False)
start_score_level_font = pygame.font.SysFont("arial", 30, True, True)
ms_font = pygame.font.SysFont("arial", 100, True, True)
ms_font2 = pygame.font.SysFont("arial", 40, True, False)

#출력
ID_text_screen = ID_font.render("ID : chamchi", True, WHITE) #첫화면 ID
ID_text = ID_font.render("chamchi", True, WHITE) #게임 화면 ID
text = start_score_level_font.render("start", True, WHITE)
ms_text = ms_font.render("pygame", True, WHITE)  #첫화면
ms_text2 = ms_font2.render("click the start button to play", True, WHITE) #첫화면
inform = ID_font.render("inform", True, WHITE)
time = ID_font.render("time : ", True, WHITE)
best = ms_font.render(f"Best score {1}", True, WHITE)
cur = ms_font2.render(f"curr_level {curr_level}", True, WHITE)
restart = start_score_level_font.render("restart", True, WHITE)
cur_d = start_score_level_font.render("level : ", True, WHITE)
cur_d_te = start_score_level_font.render(f"{curr_level}", True, WHITE)






t0 = pygame.time.get_ticks() /1000



# 게임 시작 여부

# 게임 시작 전에 게임 설정 함수 수행


# 게임 루프
running = True # 게임이 실행중인가?
while running:
    click_pos = None

    # 이벤트 루프
    for event in pygame.event.get(): # 어떤 이벤트가 발생하였는가?
        if event.type == pygame.QUIT: # 창이 닫히는 이벤트인가?
            running = False # 게임이 더 이상 실행중이 아님
        elif event.type == pygame.MOUSEBUTTONUP: # 사용자가 마우스를 클릭했을때
            click_pos = pygame.mouse.get_pos()
            print(click_pos)

    # 화면 전체를 까맣게 칠함
    screen.fill(BLACK)


    t1 = pygame.time.get_ticks() / 1000 #t0 = pygame.time.get_ticks() /1000
    dt = float(t1 - t0)

    if start == True:
        display_game_screen()# 게임 화면 표시
    elif sc == True:
        display_score_screen()
        t0 = t1
        
    elif start == False:
        display_start_screen()
        t0 = t1
    # 사용자가 클릭한 좌표값이 있다면 (어딘가 클릭했다면)
    if click_pos:
        check_buttons(click_pos)

    
    timer_text = ID_font.render(f"{dt:.3f}", True, WHITE)

    
    
    # 화면 업데이트
    pygame.display.update()

pygame.time.delay(1000)

# 게임 종료
pygame.quit()