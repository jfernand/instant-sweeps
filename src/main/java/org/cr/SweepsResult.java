package org.cr;

import lombok.Getter;

import java.util.Random;

public class SweepsResult<T> {
    private static Random rng = new Random();
    private int entries = 0;
    @Getter
    private T winnerId;


    void consider(Event<T> event) {
        entries += 1;
        if (entries == 1) {
            winnerId = event.getId();
            mark("I");
            return;
        }

        long draw = Math.abs(rng.nextLong()) % entries;
        markDraw(event.getId(), draw);
        if (draw == 1) {
            winnerId = event.getId();
            mark("R");
        }
//        System.out.println();
    }

    SweepsResult<T> merge(SweepsResult sweeps) {
        markMerge(sweeps);
        assert entries == sweeps.entries;
        entries += sweeps.entries; //TODO this relies on the fact that merges seem to happen with equal-sized batches. Fix.
        return this;
    }

    private void mark(String event) {
//        System.out.println(event + " " + winnerId + " " + entries);
    }

    private void markMerge(SweepsResult sweeps) {
//        System.out.println("M " + winnerId + " " + entries + "~" + sweeps.winnerId + " " + entries);
    }

    private void markDraw(T candidate, long seed) {
//        System.out.println("D " + candidate + " " + seed + " " + entries);
    }
}

