const oktaAuthConfig = {
  issuer: "https://dev-03597207.okta.com/oauth2/default",
  clientId: "0oap62157slAMwXrl5d6",
  redirectUri: window.location.origin + "/login/callback",
};

const oktaSignInConfig = {
  baseUrl: "https://dev-03597207.okta.com",
  clientId: "0oap62157slAMwXrl5d6",
  redirectUrl: window.location.origin + "/login/callback",
  authParams: {
    issuer: "https://https://dev-03597207.okta.com",
  },
};
export { oktaAuthConfig, oktaSignInConfig };
