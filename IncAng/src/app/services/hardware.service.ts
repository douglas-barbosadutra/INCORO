import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HardwareDTO } from 'src/dto/HardwareDTO';

@Injectable({
  providedIn: 'root'
})
export class HardwareService {

  constructor(private http: HttpClient) { }

  insertHardware(hardwareDTO: HardwareDTO) {
    return this.http.post( 'http://localhost:8080/Hardware/insertHardware', hardwareDTO);
  }

  showHardware(){
    return this.http.get('http://localhost:8080/Hardware/showHardware');
  }

  deleteHardware(idHardware: number){
    return this.http.delete('http://localhost:8080/Hardware/deleteHardware?idHardware=' + idHardware);
  }

  delete(hardwareDTO: HardwareDTO){
    return this.http.delete('http://localhost:8080/Hardware/delete?idHardware=' + hardwareDTO.idHardware);
  }

  updateHardware(hardwareDTO: HardwareDTO) {
    return this.http.put( 'http://localhost:8080/Hardware/updateHardware', hardwareDTO);
  }

  findHardware(idHardware: number){
    return this.http.get('http://localhost:8080/Hardware/findHardware?idHardware='+idHardware);
  }
}
