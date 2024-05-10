export interface User {
    id: number;
    username: string;
    name: string;
    created_at: string;
    updated_at: string;
  }
  
  export interface Jwt {
    token: any;
    jwt?: string;
  }