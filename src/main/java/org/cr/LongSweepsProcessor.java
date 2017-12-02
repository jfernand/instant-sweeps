package org.cr;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Random;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@AllArgsConstructor
public class LongSweepsProcessor implements SweepsProcessor<Long> {

//    private Stream<EntryEvent> stream = new Random().longs(10000, 0, 1000).mapToObj(EntryEvent::new);

    private Stream<Event<Long>> stream;

    @Override
    public Long getWinner()
    {
        return stream
//            .parallel()
            .collect(
            SweepsResult<Long>::new,
            SweepsResult<Long>::consider,
            SweepsResult::merge)
            .getWinnerId();
    }
}
