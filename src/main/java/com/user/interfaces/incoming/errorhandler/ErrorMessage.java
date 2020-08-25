package com.user.interfaces.incoming.errorhandler;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {

	private String code;

	private String message;

	private String info;

}
