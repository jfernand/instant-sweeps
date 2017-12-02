package org.cr;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LongEntryEvent implements Event<Long> {
    private long id;

    @Override
    public Long getId() {
        return id;
    }
}
