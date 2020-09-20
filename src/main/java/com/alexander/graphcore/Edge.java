package com.alexander.graphcore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge<V> {

    private V from;
    private V to;
}
