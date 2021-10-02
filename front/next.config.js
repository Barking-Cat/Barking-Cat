/** @type {import('next').NextConfig} */
console.log('#config', process.env.NEXT_PUBLIC_SERVER_API);
module.exports = {
  reactStrictMode: true,
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: `${process.env.NEXT_PUBLIC_SERVER_API}/api/:path*`,
      },
    ];
  },
};
