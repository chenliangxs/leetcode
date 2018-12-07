// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//
//
// The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
//
// Example:
//
// Input: [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
// ==============================================================
//


public int trap(int[] height) {
    if(height.length < 3) return 0;
    int count = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    for(int i=0; i<height.length; i++){
        if(stack.isEmpty() || height[stack.peekFirst()] > height[i]){
            stack.offerFirst(i);
        }else{
            while(!stack.isEmpty() && height[stack.peekFirst()] <= height[i]){
                int base = height[stack.pollFirst()];
                if(stack.isEmpty()) break;
                int left = stack.peekFirst();
                count += (Math.min(height[left], height[i]) - base) * (i - left - 1);
            }
            stack.offerFirst(i);
        }
    }
    return count;
}


public int trap(int[] height) {
    int total = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    for(int i = 0; i < height.length; i++){
        int curHeight = height[i];
        while(!stack.isEmpty() && curHeight >= height[stack.peekFirst()]){
            int baseHeight = height[stack.pollFirst()];
            if(!stack.isEmpty()){
                total += (Math.min(height[stack.peekFirst()], curHeight) - baseHeight) * (i - stack.peekFirst() - 1);
            }
        }
        stack.offerFirst(i);
    }
    return total;
}