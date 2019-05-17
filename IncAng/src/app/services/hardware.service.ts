import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HardwareDTO } from '../../../src/dto/HardwareDTO';

@Injectable({
  providedIn: 'root'
})
export class HardwareService {

  constructor(private http: HttpClient) { }

  insertHardware(hardwareDTO: HardwareDTO) {
    return this.http.post( 'http://localhost:8080/Hardware/insertHardware', hardwareDTO);
    console.log(hardwareDTO.master);
  }

  showHardware(){
    return this.http.get('http://localhost:8080/Hardware/showHardware');
  }

  deleteHardware(hardwareDTO: HardwareDTO){
    alert(hardwareDTO.idHardware);
    return this.http.delete('http://localhost:8080/Hardware/deleteHardware?id=' + hardwareDTO.idHardware);
  }
  
  updateHardware(hardwareDTO: HardwareDTO) {
    return this.http.put( 'http://localhost:8080/Hardware/updateHardware', hardwareDTO);
  }

  findHardware(idHardware: number){
    return this.http.get('http://localhost:8080/Hardware/findHardware?idHardware='+idHardware);
  }
}
