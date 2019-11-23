class Solution {
    public void solveSudoku(char[][] board) {
        int n = board.length, cnt = 0;
        boolean[][] rows = new boolean[n][n], cols = new boolean[n][n], boxes = new boolean[n][n];
        List<int[]> empty = new ArrayList();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]!='.'){
                    int index = board[i][j]-'1';
                    rows[i][index] = true; cols[j][index] = true; boxes[i/3*3+j/3][index] = true;
                }else{
                    empty.add(new int[]{i, j});
                }
            }
        }
        for(int k=1;k<10;k++){
            if(valid(rows, cols, boxes, board, empty,0, k)){
                return;
            }
        }


    }

    public boolean valid(boolean[][] rows, boolean[][] cols, boolean[][] boxes, char[][] board,
                         List<int[]> empty, int index,int num){
        if(index==empty.size()) return true;
        int i = empty.get(index)[0], j = empty.get(index)[1];
        if(rows[i][num-1]||cols[j][num-1]||boxes[i/3*3+j/3][num-1]) return false;
        rows[i][num-1] = true; cols[j][num-1] = true; boxes[i/3*3+j/3][num-1] = true;
        board[i][j] = (char)(num+'0');
        for(int k=1;k<10;k++){
            if(valid(rows, cols, boxes, board, empty, index+1, k)) return true;
        }
        rows[i][num-1] = false; cols[j][num-1] = false; boxes[i/3*3+j/3][num-1] = false;
        board[i][j] = '.';
        return false;
    }

}