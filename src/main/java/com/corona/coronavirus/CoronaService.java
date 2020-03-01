package com.corona.coronavirus;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import com.corona.coronavirus.model.DataModel;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

@Service
public class CoronaService {
	private List<DataModel> dataList = new ArrayList<DataModel>();
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchData() throws IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader in = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			DataModel rec = new DataModel();
			rec.setState(record.get("Province/State"));
			rec.setCountryRegion(record.get("Country/Region"));
			rec.setLastCasesNum(Integer.parseInt(record.get(record.size() - 1)));
			rec.setDiffFromPreviosDay(Integer.parseInt(record.get(record.size() - 1))-Integer.parseInt(record.get(record.size() - 2)));
			dataList.add(rec);
		}
		dataList.forEach(a->System.out.println(a));
	}

	public List<DataModel> getDataList() {
		return dataList;
	}
	
}
