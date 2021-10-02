# Barking Cat Web

## Contributor

- @seonhocho

## Development

### Prerequisite

루트 폴더에서 .env.local 파일을 생성하여 server api를 명시해 주어야 합니다.

```
NEXT_PUBLIC_SERVER_API=http://15.164.192.50

```

```
yarn dev
```

### Jest / RTL(React Testing Library)

```
yarn test:watch
```

## Production

### local

```
yarn build && yarn start
```

### docker

```
yarn docker && yarn start:production
```
