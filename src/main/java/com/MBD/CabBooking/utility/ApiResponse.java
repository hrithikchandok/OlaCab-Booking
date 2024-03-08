package com.MBD.CabBooking.utility;

import org.apache.logging.log4j.message.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
  private String message;
  private boolean success;

  
}
