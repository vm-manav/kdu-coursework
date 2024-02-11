const { defineConfig } = require("cypress");
const getCompareSnapshotsPlugin = require("cypress-visual-regression/dist/plugin");

module.exports = defineConfig({
  trashAssetsBeforeRuns: true,

  video: false,

  e2e: {
    baseUrl: "http://manav-fe-assignment-1.s3-website.ap-south-1.amazonaws.com",

    setupNodeEvents(on, config) {
      getCompareSnapshotsPlugin(on, config);
    },
  },
  env: {
    EMAIL: "test@gmail.com",
    PASSWORD: "test",
    USERNAME: "test-user",
    NAME: "test-name",
    HOME_PAGE_URL: "/index.html",
    LOGIN_PAGE_URL: "login/index.html",
    REGISTER_PAGE_URL: "/register/index.html",
    TEST_THRESHOLD: 0.2,
  },
});
