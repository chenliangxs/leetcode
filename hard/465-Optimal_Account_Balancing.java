/**
A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
**/
public int minTransfers(int[][] transactions) {
    Map<Integer, Integer> balance = new HashMap<>();
    for(int[] transaction : transactions) {
        int p1 = transaction[0];
        int p2 = transaction[1];
        balance.put(p1, balance.getOrDefault(p1, 0) + transaction[2]);
        balance.put(p2, balance.getOrDefault(p2, 0) - transaction[2]);
    }
    List<Integer> remain = new ArrayList<>();
    for(int key : balance.keySet()) {
        if(balance.get(key) != 0) {
            remain.add(balance.get(key));
        }
    }
    return dfs(0, remain);
}
public int dfs(int index, List<Integer> remain) {
    if(index >= remain.size()) {
        return 0;
    }
    int res = Integer.MAX_VALUE;
    if(remain.get(index) == 0) {
        return dfs(index + 1, remain);
    }
    for(int i = index + 1; i < remain.size(); i++) {
        if(remain.get(i) * remain.get(index) < 0) {
            int tmp1 = remain.get(index);
            int tmp2 = remain.get(i);
            remain.set(index, 0);
            remain.set(i, tmp1 + tmp2);
            int next = dfs(index + 1, remain);
            if(next != Integer.MAX_VALUE) {
                res = Math.min(res, 1 + next);
            }
            remain.set(index, tmp1);
            remain.set(i, tmp2);
        }
    }
    return res;
}
