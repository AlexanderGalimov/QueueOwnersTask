package ru.vsu.cs.galimov.tasks;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LinkedQueueTest {

    @Test
    public void countTimeOfDeal() {
        LinkedQueue q1 = new LinkedQueue(new LinkedQueue.OwnerInQueue(0, 15, 10));

        q1.addOwner(10, 6, 5);
        q1.addOwner(10, 7, 10);
        q1.addOwner(40, 20, 30);
        q1.addOwner(43, 18, 10);
        q1.addOwner(100, 20, 10);
        q1.addOwner(115, 10, 10);
        q1.addOwner(120, 20, 18);
        q1.addOwner(130, 10, 16);
        q1.addOwner(132, 8, 5);
        q1.addOwner(133, 7, 10);
        q1.addOwner(180, 10, 16);
        System.out.println(q1);

        int[] test1 = q1.countTimeOfDeal();

        System.out.println(Arrays.toString(test1));

        int[] answer1 = {25, 30, 40, 90, 100, 130, 140, 158, 174, 179, 189, 206};

        assertArrayEquals(test1,answer1);

        LinkedQueue q2 = new LinkedQueue(new LinkedQueue.OwnerInQueue(0,10,5));
        q2.addOwner(1,2,4);
        q2.addOwner(2,6,10);
        q2.addOwner(0,10,20);
        q2.addOwner(10,14,20);
        q2.addOwner(10,3,10);
        q2.sort();
        System.out.println(q2);

        int[] res = q2.countTimeOfDeal();

        System.out.println(Arrays.toString(res));

        int[] answer3 = {7,18,23,43,53,73};

        assertArrayEquals(res,answer3);
    }
}