
    public static List<Integer> oddNumbers(int l, int r) {
    // Write your code here
    List<Integer> list = new ArrayList<Integer>();
    if(l%2==0) {
        list.add(l+1);
    }
    else {

        list.add(l);
    }
    for(int i=1;i<r-l;i++) {
        if(list.get(i-1)+2>r) {
            break;
        }
        list.add(list.get(i-1)+2);
    }
    return list;
    }