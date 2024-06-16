export interface CustomErrorResponse {
    status: number;
    message: string;
    timeStamp: number;
    validationErrors: Array<string>;
}