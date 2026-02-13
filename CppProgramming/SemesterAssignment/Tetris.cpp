#include "tetrismodel.h"
#include <QDebug>

TetrisModel::TetrisModel():
    cells(Tetris::HEIGHT, QList<Cell*>(Tetris::WIDTH, &CellFactory::emptyCell)),
    nextCells(Tetris::NEXT_HEIGHT, QList<Cell*>(Tetris::NEXT_WIDTH, &CellFactory::emptyCell))
{
}

void TetrisModel::init()
{
    level = 1;
    score = 0;
    numberOfLines = 0;
    clearCells();
}

void TetrisModel::clearCells() noexcept
{
    for(int r{0}; r < Tetris::HEIGHT; ++r)
        for(int c{0}; c < Tetris::WIDTH; ++c)
            cells[r][c] = &CellFactory::emptyCell;
}

void TetrisModel::clearNextCells() noexcept
{
    for(int r{0}; r < Tetris::NEXT_HEIGHT; ++r)
        for(int c{0}; c < Tetris::NEXT_WIDTH; ++c)
            nextCells[r][c] = &CellFactory::emptyCell;
}

void TetrisModel::next() {
    tetrominoFactory.next();
}

// 새 테트로미노 추가: 항상 추가 위치는 (0, 3)
// 테트로미노를 정의한 형태 (4x4, 첫 행 빈 행) 때문에 실제 첫 행에는 테트로미노가 나타나지 않음
// 새 테트로미노를 추가할 수 없으면 게임 종료 (반환 값을 이용하여 판단)
bool TetrisModel::insertTetromino() {
    tetrominoLoc.set(0, 3);
    currTetromino = &tetrominoFactory.getCurrent();
    if(isEmptyRow(1) && canMove(tetrominoLoc, currTetromino->getCurrentBlock())) {
        applyBlock();
        insertNextTetromino();
        return true;
    }
    return false;
}

void TetrisModel::insertNextTetromino() {
    clearNextCells();
    Tetromino nextTetromino = tetrominoFactory.getNext();
    const QList<QList<int>>& blockShape{nextTetromino.getCurrentBlock()};
    Location startLoc(1, 1);
    for(int r{0}; r < 4; ++r){
        int row{r + startLoc.r};
        for(int c{0}; c < 4; ++c) {
            int col{c + startLoc.c};
            if(blockShape[r][c] == 1)
                nextCells[row][col] = CellFactory::getCell(nextTetromino.shapeType);
        }
    }
}

bool TetrisModel::moveTetrominoDown()
{
    return move(Location{tetrominoLoc.r + 1, tetrominoLoc.c});
}

// 현재 위치에서 지우고(eraseBlock),
// 새 위치에 그릴 수 있는지 검사(canMove)함
// 그릴 수 있으면 테트로미노 위치를 변경하고, 새 위치에 추가함 (applyBlock)
// 없으면 현 위치 다시 추가함 (applyBlock)
bool TetrisModel::move(const Location& newLoc)
{
    bool flag{false};
    eraseBlock();
    if(canMove(newLoc, currTetromino->getCurrentBlock())){
        tetrominoLoc.set(newLoc.r, newLoc.c);
        flag = true;
    }
    applyBlock();
    return flag;
}

// 현재 행이 빈 행이면 행의 빈 셀 수는 열 크기와 같아야 함
bool TetrisModel::isEmptyRow(int r) const noexcept {
    int sum{0};
    for(int c{0}; c < Tetris::WIDTH; ++c)
        sum += cells[r][c]->empty? 1: 0;
    return sum == Tetris::WIDTH;
}

// 현재 테트로미노를 tetrominoLoc 위치에서 제거함 
// 테트로미노가 1인 위치를 다시 emptyCell로 바꿈
// cells[][] = &CellFactory::emptyCell;
void TetrisModel::eraseBlock() {
    const QList<QList<int>>& blockShape{currTetromino->getCurrentBlock()};
    for(int r{0}; r < 4; ++r){
        for(int c{0}; c < 4; ++c) {
            if(blockShape[r][c] == 1)
                cells[tetrominoLoc.r + r][tetrominoLoc.c + c] = &CellFactory::emptyCell;
        }
    }
}


// 현재 테트로미노를 tetrominoLoc 위치에 추가함 
// 테트로미노가 1인 위치를 해당 Cell 타입으로 바꿈
// cells[][] = CellFactory::getCell(currTetromino->shapeType);
void TetrisModel::applyBlock() {
    const QList<QList<int>>& blockShape{currTetromino->getCurrentBlock()};
    for(int r{0}; r < 4; ++r){
        for(int c{0}; c < 4; ++c) {
            if(blockShape[r][c] == 1)
                cells[tetrominoLoc.r + r][tetrominoLoc.c + c] = CellFactory::getCell(currTetromino->shapeType);
        }
    }
}


// 주어진 위치에 테트로미노를 추가할 수 있는지 검사함
// 조건 1. 위치가 유효해야 함
// 조건 2. shapeBlock[][]가 1인 경우 cells[][]는 empty이어ㅑ 함
bool TetrisModel::canMove(const Location& newLoc, const QList<QList<int>>& shapeBlock) {
    for(int r{0}; r < 4; ++r){
        for(int c{0}; c < 4; ++c) {
            if(shapeBlock[r][c] == 1) {
                int row = newLoc.r + r;
                int col = newLoc.c + c;
                // 위치 유효성
                if (row < 0 || row >= Tetris::HEIGHT || col < 0 || col >= Tetris::WIDTH)
                    return false;
                // 빈 셀이 아니라면?
                if (!cells[row][col]->empty)
                    return false;
            }
        }
    }
    return true;
}