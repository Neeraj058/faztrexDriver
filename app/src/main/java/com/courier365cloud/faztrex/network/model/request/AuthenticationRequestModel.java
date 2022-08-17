package com.courier365cloud.faztrex.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationRequestModel {

    // region Login
    public class LoginRequest {

        @SerializedName("Username")
        @Expose
        private String userName;

        @SerializedName("Password")
        @Expose
        private String password;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
    // endregion

    // region Forgot Password
    public class ForgotPasswordRequest {

        @SerializedName("UserEmail")
        @Expose
        private String emailAddress;

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }
    }
    // endregion

    // region Reset Password
    public class ResetPasswordRequest {

        @SerializedName("UserId")
        @Expose
        private String userId;

        @SerializedName("Password")
        @Expose
        private String newPassword;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
    // endregion

    // region Change Password
    public class ChangePasswordRequest {

        @SerializedName("UserId")
        @Expose
        private String userId;

        @SerializedName("CurrentPassword")
        @Expose
        private String currentPassword;

        @SerializedName("NewPassword")
        @Expose
        private String newPassword;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCurrentPassword() {
            return currentPassword;
        }

        public void setCurrentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
    // endregion
}
