


public class Percolation {
    
    public static void main(String[] args){
        Percolation percolator = new Percolation(StdIn.readInt());
    }
    public int N;
    private boolean[] binaryGrid;
    public WeightedQuickUnionUF grid;
    
    public Percolation(int N){
        this.N = N;
        this.binaryGrid = new boolean[(N * N) + 1];
        this.grid = new WeightedQuickUnionUF((N * N) + 1);
        for (int i = 1; i <= N; i++){
            this.grid.union(i, 0 );
            this.grid.union(((N - 1) * N) + i, N * N );
        }

    }
    public void open(int i, int j){
     
        System.out.println(i + " " + j);
        this.checkRangeAndThrowExceptionIfOutside(i, j);
        this.binaryGrid[(j * this.N) + i] = true;
        if(i >= 1){
            if (this.binaryGrid[(j * this.N) + (i - 1)]){
                this.grid.union((j * this.N) + (i - 1), (j * this.N) + i);

            }   
        }
        if(i < this.N - 1){
            if (this.binaryGrid[(j * this.N) + (i + 1)]){
                this.grid.union((j * this.N) + (i + 1), (j * this.N) + i);
            }
        }
        if(j >= 1){
            if (this.binaryGrid[(j - 1) * this.N + i]){
                this.grid.union(((j - 1) * this.N) + i, (j * this.N) + i);
            }
        }
        if(j < this.N - 1){
            if (this.binaryGrid[(j + 1) * this.N + i]){
                this.grid.union(((j + 1) * this.N) + i, (j * this.N) + i);
            }
        }
    }
    public boolean isOpen(int i, int j){
        this.checkRangeAndThrowExceptionIfOutside(i, j);
        return this.binaryGrid[(j - 1) * this.N + i];
    }
    public boolean isFull(int i, int j){
        i--;
        j--;
        this.checkRangeAndThrowExceptionIfOutside(i, j);
        return this.grid.connected(0, this.N * j + i);
    }
    public boolean percolates(){
        return this.grid.connected(0, (this.N * this.N) );
    }
    private void checkRangeAndThrowExceptionIfOutside(int i, int j){
        if(i < 0 || i > this.N ||
           j < 0 || j > this.N){
            throw new java.lang.IndexOutOfBoundsException(i + " " + j + " " + "is out of range");
        }
    }
}