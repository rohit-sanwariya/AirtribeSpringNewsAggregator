import { Component, inject, OnInit, output, signal } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PreferencesDialogComponent } from '../preferences-dialog/preferences-dialog.component';
import { HttpService } from '../../Services/http.service';
import { Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { PreferenceCreateDialogComponent } from '../preference-create-dialog/preference-create-dialog.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatSelectModule,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  allPreference: { id: number; name: string; }[] = [];
  _http = inject(HttpService);
  _router = inject(Router);
  afterUpdatePreference = output<boolean>();
  selectedPreference: { id: number; name: string; }[] = [];
  showdropdown = signal(false);
  constructor(private dialog: MatDialog) {}
  ngOnInit(): void {
    this.getPreferences();
    this.getUserPreferences();
  }

  openPreferencesUpdate(): void {
    const dialogRef = this.dialog.open(PreferencesDialogComponent, {
      width: '50rem',
      height:'25rem',
      data:{
        selectedPreferences:this.selectedPreference,
        allPreferences : this.allPreference
      }
    });

   const sub =  dialogRef.afterClosed().subscribe((result:Array<number>) => {
      if (result) {
        this.updatePreference(result);   
        dialogRef.close();
        sub.unsubscribe();    
      }
      
    });
  }
  openPreferencesCreate(): void {
    const dialogRef = this.dialog.open(PreferenceCreateDialogComponent, {
      width: '50rem',
      height:'25rem',
      
    });

    const sub = dialogRef.afterClosed().subscribe((result:string) => {
      if (result) {
        this.createPreference(result);
        sub.unsubscribe();
      }
    });
  }
  createPreference(preferencename:string):void{
    this._http.GET<Array<{id:number,name:string}>>(`/api/preferences/create/${preferencename}`).subscribe({
      next: () => {
        this.getPreferences();
      }
    })
  }
  logout():void{
    localStorage.clear();
    this._router.navigate(['/login']);
  }
  getPreferences():void{
    this._http.GET<Array<{id:number,name:string}>>("/api/preferences/all").subscribe({
      next: (preferences) => {
        this.allPreference = preferences;
      }
    })
  }

  getUserPreferences():void{
    this._http.GET<Array<{id:number,name:string}>>("/api/preferences").subscribe({
      next: (preferences) => {
        this.selectedPreference = preferences;
      }
    })
  }
  updatePreference(ids:Array<number>){
    this._http.PUT('/api/preferences/update',ids).subscribe(()=>{
      this.selectedPreference = this.allPreference.filter(p => ids.includes(p.id));
      this.afterUpdatePreference.emit(true);
       
    });
  }


showdropdownTogggle() {
    this.showdropdown.update((value)=>!value)
  }
}
