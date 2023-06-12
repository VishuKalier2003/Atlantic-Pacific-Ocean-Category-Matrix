/* There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean 
touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where 
heights[r][c] represents the height above sea level of the cell at coordinate (r, c). The island receives a lot of 
rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's
height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the 
ocean. Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from 
cell (ri, ci) to both the Pacific and Atlantic oceans.
* Eg 1 :  grid = [[1,2,2,3,5],                                                         
*                 [3,2,3,4,4],                                                         
*                 [2,4,5,3,1],    Output = [[0,4],[1,3],[1,4],[2,2],[3,0],[4,0],[3,1]] 
*                 [6,7,1,4,5],                                                         
*                 [5,1,1,2,4]]                                                         
* Eg 2 :  grid = [[1]]            Output = [[0,0]]                                     
*/
import java.util.*;
public class PacificAtlantic
{
      public List<List<Integer>> DrainedByBoth(int grid[][])
      {
            List<List<Integer>> output = new ArrayList<List<Integer>>();
            boolean pacificDP[][] = new boolean[grid.length][grid[0].length];   //* DP Matrix -> O(N x M)
            boolean atlanticDP[][] = new boolean[grid.length][grid[0].length];  //* DP Matrix -> O(N x M)
            for(int i = 0; i < pacificDP.length; i++)      //! DP Base condition -> O(N)
                  pacificDP[i][0] = true;    // defined base case...
            for(int i = 0; i < pacificDP[0].length; i++)   //! DP Base condition -> O(M)
                  pacificDP[0][i] = true;
            for(int i = 0; i < atlanticDP.length; i++)     //! DP Base condition -> O(N)
                  atlanticDP[i][atlanticDP.length - 1] = true;    // defined base case...
            for(int i = 0; i < atlanticDP[0].length; i++)  //! DP Base condition -> O(M)
                  atlanticDP[0][atlanticDP[0].length - 1] = true;
            pacificDP = DrainedByPacific(grid, pacificDP);       //! Function Call -> O(N x M)
            atlanticDP = DrainedByAtlantic(grid, atlanticDP);    //! Function Call -> O(N x M)
            for(int i = 0; i < pacificDP.length; i++)    //! Grid Traversal -> O(N x M)
            {
                  int index = 0;    // Indexing variable...
                  for(int j = 0; j < atlanticDP[0].length; j++)
                  {
                        if((pacificDP[i][j] == true) && (atlanticDP[i][j] == true))
                        {     // If a cell is drained by both the Oceans, then add it into the output list...
                              output.add(index, Arrays.asList(i, j));   // Adding x and y coordinate...
                              index++;
                        }
                  }
            }
            DisplayGridDrainage(pacificDP, "Pacific");       // Function call to print the Drainage Cells...
            DisplayGridDrainage(atlanticDP, "Atlantic");;    // Function call to print the Drainage Cells...
            return output;
      }
      public boolean[][] DrainedByPacific(int grid[][], boolean pacificDP[][])
      {
            for(int i = 1; i < grid.length; i++)     //! Grid Traversal -> O(N x M)
            {
                  for(int j = 1; j < grid[0].length; j++)
                  {     // If either left or upper cell is drained and lower in height...
                        if(((pacificDP[i][j - 1] == true) && (grid[i][j - 1] <= grid[i][j])) || ((pacificDP[i - 1][j]
                         == true) && (grid[i - 1][j] <= grid[i][j])))
                              pacificDP[i][j] = true;    // Set the value of the cell as drained...
                        else
                              pacificDP[i][j] = false;   // Otherwise set as not drained...
                  }
            }
            return pacificDP;
      }
      public boolean[][] DrainedByAtlantic(int grid[][], boolean atlanticDP[][])
      {
            for(int i = grid.length - 2; i > -1; i--)     //! Grid Traversal -> O(N x M)
            {
                  for(int j = grid[0].length - 2; j > -1; j--)
                  {     // If either right or bottom cell is drained and lower in height...
                        if(((atlanticDP[i][j + 1] == true) && (grid[i][j + 1] <= grid[i][j])) || ((atlanticDP[i + 1]
                        [j] == true) && (grid[i + 1][j] <= grid[i][j])))
                              atlanticDP[i][j] = true;    // Set the value of the cell as drained...
                        else
                              atlanticDP[i][j] = false;    // Otherwise set as not drained...
                  }
            }
            return atlanticDP;
      }
      public void DisplayGrid(int grid[][])
      {
            System.out.println("The Grid formed is : ");
            for(int i = 0; i < grid.length; i++)                  //! Grid Traversal -> O(N x M)
            {
                  for(int j = 0; j < grid[0].length; j++)
                        System.out.print(grid[i][j]+", ");
                  System.out.println();
            }
      }
      public void DisplayGridDrainage(boolean grid[][], String str)
      {
            System.out.println("The Drainage Map of "+str);
            for(int i = 0; i < grid.length; i++)               //! Grid Traversal -> O(N x M)
            {
                  for(int j = 0; j < grid[0].length; j++)
                  {
                        if(grid[i][j] == true)
                              System.out.print(1+", ");
                        else
                              System.out.print(0+", ");
                  }
                  System.out.println();
            }
      }
      public static void main(String args[])
      {
            Scanner sc = new Scanner(System.in);
            int row, col;
            System.out.print("Enter number of Rows : ");
            row = sc.nextInt();
            System.out.print("Enter number of Columns : ");
            col = sc.nextInt();
            int grid[][] = new int[row][col];
            for(int i = 0; i < grid.length; i++)
            {
                  for(int j = 0; j < grid[0].length; j++)
                  {
                        System.out.print("Enter height of "+(i+1)+" row and "+(j+1)+" column : ");
                        grid[i][j] = sc.nextInt();
                  }
            }
            PacificAtlantic pacificAtlantic = new PacificAtlantic();       // Object creation...
            pacificAtlantic.DisplayGrid(grid);                  // Helper Function call...
            List<List<Integer>> lst = new ArrayList<List<Integer>>();      // Function call...
            lst = pacificAtlantic.DrainedByBoth(grid);
            for(int i = 0; i < lst.size(); i++)
            {
                  for(int j = 0; j < lst.get(i).size(); j++)
                        System.out.print("["+lst.get(i).get(0)+", "+lst.get(i).get(1)+"], ");
                  System.out.println();
            }
            sc.close();
      }
}



//! Time Complexity -> O(N x M)
//* Space Complexity -> O(N x M)