import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Inject } from '@angular/core';
import { MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateModule } from '@ngx-translate/core'; // Import TranslateModule

@Component({
  selector: 'app-sending-resources',
  templateUrl: './sending-resources.component.html',
  styleUrls: ['./sending-resources.component.scss'],
  standalone: true,
  imports: [MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, CommonModule, TranslateModule], // Add TranslateModule to imports
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SendingResourcesComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { needForSupply: boolean; reourcesUpdated: boolean }) {}
}
