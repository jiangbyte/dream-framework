/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_API_URL: string
  readonly VITE_HOME_PATH: string
  readonly VITE_ROUTES_MODE: string // 'static' | 'dynamic'
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
