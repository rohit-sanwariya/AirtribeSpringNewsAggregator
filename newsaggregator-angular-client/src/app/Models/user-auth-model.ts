// Define the types for nested objects
interface Role {
    id: number;
    name: string;
  }
  
  interface Token {
    id: number;
    token: string;
    expiresAt: string | null; // or Date if you prefer to handle dates directly
  }
  
  // Define the main response type
  export interface AuthUserResponse {
    httpStatus: string;
    data: {
      id: number;
      roles: Role[];
      username: string;
      firstname: string;
      lastname: string;
      token: Token;
    };
    message: string;
  }
  