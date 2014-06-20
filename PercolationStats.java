

public class PercolationStats {
    
   Percolation percolator;
   public int T;
   private int iterationsUntilPercolation;
   public PercolationStats(int N, int T){
        this.T = T;
        percolator = new Percolation(N);
        
        if (N <= 0 || T <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        
        int iterator = 0;
        this.iterationsUntilPercolation = 0;
        boolean hasCollectedPercInfo = false;
        while(iterator != T){
            int i = StdRandom.uniform(1, N + 1);
            int j = StdRandom.uniform(0, N);
            if(this.percolator.isOpen(i, j)){
                continue;
            }
            this.percolator.open(i, j);
            iterator++;
            if(this.percolator.percolates() && !hasCollectedPercInfo){
                hasCollectedPercInfo = true;
                iterationsUntilPercolation = iterator;
            }
        }
   }
   
   public double mean(){
       return (double)this.iterationsUntilPercolation / (this.percolator.N * this.percolator.N);
   }
   /*
   public double stddev(){
       
   }
   public double confidenceLo(){
       
   }
   public double confidenceHi(){
       
   }
   */
   public static void main(String[] args){
       PercolationStats stats = new PercolationStats(StdIn.readInt(), StdIn.readInt());
       System.out.println(stats.percolator.percolates() + " " + stats.percolator.grid.count() + " " + stats.mean());
   }
}