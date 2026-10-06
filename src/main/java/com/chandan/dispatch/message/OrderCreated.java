package com.chandan.dispatch.message;


import lombok.*;

import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreated {

    UUID orderId;
    String item;
}
