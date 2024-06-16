/* tslint:disable */
/* eslint-disable */
import { ScanningDeviceDto } from '../models/scanning-device-dto';
export interface CarCheckDto {
  carStatus?: 'FULL_CHECK' | 'RESOURCES_CHECK' | 'TRAVEL_CHECK';
  id?: number;
  localDateTime?: string;
  scanningDeviceDTO?: ScanningDeviceDto;
  supplyCarId?: number;
}
