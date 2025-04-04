import { User } from "./user";

export interface AuthResponse {
    token: string;
    userDTO: User
}
