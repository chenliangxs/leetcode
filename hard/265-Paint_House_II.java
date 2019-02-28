/**
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Follow up:
Could you solve it in O(nk) runtime?

========================================================
**/

public int minCostII(int[][] costs) {
    if(costs.length == 0 || costs[0].length == 0) return 0;
    int houses = costs.length;
    int colors = costs[0].length;
    int[][] dp = new int[houses][colors];
    for(int i=0; i<colors; i++){
        dp[0][i] = costs[0][i];
    }
    for(int house = 1; house < houses; house++){
        for(int color = 0; color < colors; color++){
            int min = Integer.MAX_VALUE;
            for(int preColor = 0; preColor < colors; preColor++){
                if(preColor == color) continue;
                else{
                    min = Math.min(dp[house - 1][preColor], min);
                }
            }
            dp[house][color] = min + costs[house][color];
        }
    }
    int finalMin = Integer.MAX_VALUE;
    for(int color=0; color<colors; color++){
        finalMin = Math.min(finalMin, dp[houses-1][color]);
    }
    return finalMin;
}

O(nk)

public int minCostII(int[][] costs) {
    if(costs.length == 0 || costs[0].length == 0) return 0;
    int m = costs.length;
    int n = costs[0].length;
    int colorOne = -1;
    int colorTwo = -1;
    for(int i = 0; i < m; i++){
        int preColorOne = colorOne;
        int preColorTwo = colorTwo;
        colorOne = -1;
        colorTwo = -1;
        for(int j = 0; j < n; j++){
            if(j == preColorOne){
                costs[i][j] = (i > 0 ? costs[i-1][preColorTwo] : 0) + costs[i][j];
            }else{
                costs[i][j] = (i > 0 ? costs[i-1][preColorOne] : 0) + costs[i][j];
            }
            if(colorOne == -1 || costs[i][j] <= costs[i][colorOne]){
                colorTwo = colorOne;
                colorOne = j;
            }else if(colorTwo == -1 || costs[i][j] < costs[i][colorTwo]){
                colorTwo = j;
            }
        }
    }
    return costs[m - 1][colorOne];
}