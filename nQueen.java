class nQueen {

    /**Solves the N queen problem for any squared board.
     * solve() returns true when it finds a solution.
     * in valid() you can set certain slots invalid in
     * order to observe different solutions.
     * 0 --> Empty slot.
     * 1 --> A queen.
     */

    public static boolean solve(int[][] board){ //REARRANGE RECURSIVE LOGIC HERE.

        if (complete(board)){ //Recursive base case.
            printArray(board);
            return true;
        }

        for(int i=0; i<board.length; i++)
            for(int j=0; j<board.length; j++) {
                int[] next = nextEmpty(board, i, j);
                int row = next[0];
                int col = next[1];
                if(row==board.length)
                    return false;
                if (valid(board, row, col)){
                    board[row][col] = 1;
                    //Both lines for debugging.//uncomment to debug.
                    //printArray(board);
                    //System.out.println("-----------------");
                    ////////////////////////////////////////////
                    if (solve(board))
                        return true;
                    board[row][col] = 0;
                }
            }
        return false;
    }

    public static boolean valid(int[][] board, int row, int col){
        //uncomment and change in order to get different solutions.
        //if(row==0 && col==0) return false;
        //////////////////////////////////////////////////////

        for(int i=0; i<board.length; i++)
            if (board[row][i] == 1 && i != col)
                return false;

        for(int i=0; i<board.length; i++)
            if(board[i][col]==1 && i!=row)
                return false;

        //Calculate the diagonals
        int diag1col = Math.max(col - row, 0);
        int diag1row = Math.max(row - col, 0);
        ////WORK HERE
        int diag2col = Math.min(col + row, board.length-1);
        int diag2row = Math.min(row -(diag2col-col), board.length-1);

        //uncomment to debug.
        //System.out.println("["+diag1row+"]["+diag1col+"]\n"+"["+diag2row+"]["+diag2col+"]");

        while(diag1row < board.length && diag1col < board.length){
            if(board[diag1row][diag1col]==1 && diag1row!=row && diag1col!=col)
                return false;
            diag1col++;
            diag1row++;
        }

        while(diag2row < board.length && diag2col >= 0){
            if(board[diag2row][diag2col]==1 && diag2row!=row && diag2col!=col)
                return false;
            diag2col--;
            diag2row++;
        }

        //If passed all the checks --> position is valid.
        return true;

    }

    public static boolean complete(int[][] board){
        int counter = 0;
        for(int i=0; i<board.length; i++)
            for(int j=0; j<board[0].length; j++)
                if(board[i][j]==1)
                    counter++;
        if(counter==board.length)
            return true;
        return false;
    }

    public static int[] nextEmpty(int[][] board, int row, int col) { // [0] = row, [1] = col. Check from the last slot until reaches 1 --> then returns 1 position before.
        for(int i = board.length-1; i>=0; i--)
            for (int j = board.length-1; j>=0; j--)
                if(board[i][j] == 1){
                    if (i>row || (i==row && j>=col)) {
                        if (j == board.length - 1)
                            return new int[]{i + 1, 0};
                        else
                            return new int[]{i, j + 1};
                    }
                }
        return new int[] {row, col};
    }

    public static void printArray(int[] arr){ //For debugging.
        for(int i=0; i<arr.length; i++)
            System.out.print(arr[i]+",");
    }

    public static void printArray(int[][] arr){ //For debugging.
        String str = "";
        for(int i=0; i<arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                str += (arr[i][j] + ",");
            str += "\n";
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        // 0 represents empty slot and 1 is a full slot.
        int[][] board = {
                // 0 1 2 3 4 5 6 7
                {0,0,0,0,0,0,0,0}, //0
                {0,0,0,0,0,0,0,0}, //1
                {0,0,0,0,0,0,0,0}, //2
                {0,0,0,0,0,0,0,0}, //3
                {0,0,0,0,0,0,0,0}, //4
                {0,0,0,0,0,0,0,0}, //5
                {0,0,0,0,0,0,0,0}, //6
                {0,0,0,0,0,0,0,0}}; //7

        int[][] miniBoard = {
                // 0 1 2 3
                {0,0,0,0}, //0
                {0,0,0,0}, //1
                {0,0,0,0}, //2
                {0,0,0,0}}; //3

        System.out.println(solve(board));
    }
}