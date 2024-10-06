package com.swiggyapp1.swiggyApp1.login.service;

import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
  private ConcurrentHashMap<String, OtpDetails> otpMap = new ConcurrentHashMap<>();

  private static final int OTP_LENGTH = 6;

  public String generateOtp(String mobileNumber) {
    String otp = String.valueOf(new Random().nextInt((int) Math.pow(10, OTP_LENGTH)));
    otpMap.put(
        mobileNumber,
        new OtpDetails(otp, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)));
    return otp;
  }

  public boolean validateOtp(String mobileNumber, String otp) {
    OtpDetails otpDetails = otpMap.get(mobileNumber);

    if (otpDetails != null && otpDetails.getOtp().equals(otp)) {
      if (System.currentTimeMillis() <= otpDetails.getExpirationTime()) {
        otpMap.remove(mobileNumber);
        return true;
      }
    }
    return false; // Invalid OTP or expired
  }

  private static class OtpDetails {
    private String otp;
    private long expirationTime;

    public OtpDetails(String otp, long expirationTime) {
      this.otp = otp;
      this.expirationTime = expirationTime;
    }

    public String getOtp() {
      return otp;
    }

    public long getExpirationTime() {
      return expirationTime;
    }
  }
}
