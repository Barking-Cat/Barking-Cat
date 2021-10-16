export {};

declare global {
  interface Window {
    KaKao: {
      init: (clientKey: string) => void;
    };
  }
}
