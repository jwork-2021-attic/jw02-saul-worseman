package S191220072;

public class BubbleSorter implements Sorter {

    private int[] a;

    public void load(int[] a) {
        this.a = a;
    }

    public void bubbleSort() {

    }

    private void swap(int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        // boolean sorted = false;
        // while (!sorted) {
        //     sorted = true;
        //     for (int i = 0; i < a.length - 1; i++) {
        //         if (a[i] > a[i + 1]) {
        //             swap(i, i + 1);
        //             sorted = false;
        //         }
        //     }
        // }
        for(int i = 0; i < a.length; i++){
            int idx = i;
            for(int j = i; j < a.length; j++){
                if(a[idx] > a[j]){
                    idx = j;
                    }
                }
            swap(idx,i);
            }
        }

    @Override
    public String getPlan() {
        //System.out.println(plan);
        return this.plan;
    }

    public static void main(String[] args){

    }
}
 