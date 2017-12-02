package org;

import org.cr.EntryEvent;
import org.cr.Event;
import org.cr.LongSweepsProcessor;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

public class TestSweeps {
    private Stream<Event<Long>> stream;
    private Map<String, Integer> counts = new HashMap<>();
    private List<Map<String, Integer>> results = new ArrayList<>();
    private Map<String, List<Integer>> columns = new HashMap<>();

    @Test
    public void testSweeps() {
        for (int j = 0; j < 100; j++) {
            counts = new HashMap<>();
            for (int i = 0; i < 10000; i++) {
                stream = new Random().longs(100, 1, 11).mapToObj(EntryEvent::new);
//                    stream = LongStream.rangeClosed(1, 100).mapToObj(EntryEvent::new);

                LongSweepsProcessor p = new LongSweepsProcessor(stream);
                Long winner = p.getWinner();
//                System.out.println("Winner: " + winner);
                add(winner.toString());
            }
            add(counts);
        }
        process();
    }

    private void add(String winner) {
        counts.putIfAbsent(winner, 0);
        counts.compute(winner, (s, v) -> v = v + 1);
    }

    private void add(Map<String, Integer> map) {
        results.add(map);
    }

    private void process() {
        results.stream()
            .flatMap(m -> m.entrySet().stream())
            .forEach(
                e -> {
                    columns.putIfAbsent(e.getKey(), new ArrayList<>());
                    columns.get(e.getKey()).add(e.getValue());
                }
            );
        columns.forEach((k, l) -> System.out.println(k + " " +l.stream().sorted().mapToLong(i -> i).summaryStatistics()));
    }
}
