import {Component, OnInit} from '@angular/core';
import {SalesReportService} from "../../service/sales-report.service";
import { DecimalPipe } from '@angular/common';
import {Chart} from 'chart.js';
import Swal from "sweetalert2";
import {SalesReport} from "../../model/sales-report";
import {LenghtMessageService} from "../../service/lenght-message.service";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-sales-report',
  templateUrl: './sales-report.component.html',
  styleUrls: ['./sales-report.component.css']
})
export class SalesReportComponent implements OnInit {
  private chart: Chart | undefined;

  revenues: number[] = [];

  name: string[] = [];


  constructor(private salesReportService: SalesReportService,
              private title: Title) {
    this.salesReport()
  }

  ngOnInit(): void {
    window.scrollTo(1900,900)
    this.title.setTitle('Báo cáo bán hàng')
  }

  salesReport() {
    this.salesReportService.saleReport().subscribe(data=>{
      for (let i = 0; i < data.length; i++){
        this.revenues.push((data as any)[i].revenues);
        this.name.push((data as any)[i].name);
      }
      this.drawChart(this.name,this.revenues)
    })
  }

  drawChart(name: string[], revenues: any[]) {
    this.chart = new Chart('myChart', {
      type: 'line',
      data: {
        labels: name,
        datasets: [{
          label: 'Doanh thu',
          data: revenues,
          backgroundColor: 'rgba(255,255,0,0.28)',
          borderColor: 'black',
          borderWidth: 5,
        }],
      }
    });
  }

}
