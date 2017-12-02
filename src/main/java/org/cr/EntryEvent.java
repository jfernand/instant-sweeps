package org.cr;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntryEvent<I> implements Event<I> {
    private I id;

    @Override
    public I getId() {
        return id;
    }
}
