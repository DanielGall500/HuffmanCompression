package src.algorithms;

import src.utilities.Node;
import java.util.ArrayList;

public class SelectionSort {

    public SelectionSort() {}

    public void sort(ArrayList<Node> list, int N)
    {
        for(int i = 0; i < N - 1; i++)
        {
            int min = i;

            for(int j = (i+1); j < N; j++)
            {
                Node n 		 = list.get(j);
                Node currMin = list.get(min);

                if (n.getFrequency() < currMin.getFrequency())
                    min = j;
            }

            swap(list, i, min);
        }
    }

    private void swap(ArrayList<Node> list, int i, int j)
    {
        //Swap The Two Entries
        Node tmp = list.get(i);

        list.set(i, list.get(j));

        list.set(j, tmp);
    }
}
