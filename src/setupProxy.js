const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api/v1",
    createProxyMiddleware({
      target: "http://localhost:8080", // 프록시할 백엔드 서버 주소
      changeOrigin: true,
    })
  );
};
