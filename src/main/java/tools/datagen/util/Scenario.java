package tools.datagen.util;

import java.util.List;

public class Scenario {
	private String title;
	private List<String> testColumnTitles;
	private List<Row> testRows;

	public Scenario() {
	}

	public Scenario(String title, List<String> testColumnTitles, List<Row> testRows) {
		super();
		this.title = title;
		this.testColumnTitles = testColumnTitles;
		this.testRows = testRows;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getTestColumnTitles() {
		return testColumnTitles;
	}

	public void setTestColumnTitles(List<String> testColumnTitles) {
		this.testColumnTitles = testColumnTitles;
	}

	public List<Row> getTestRows() {
		return testRows;
	}

	public void setTestRows(List<Row> testRows) {
		this.testRows = testRows;
	}

	static class Row {
		private Long position;
		private List<String> rowData;

		public Row() {
		}

		public Row(Long position, List<String> rowData) {
			super();
			this.position = position;
			this.rowData = rowData;
		}

		public Long getPosition() {
			return position;
		}

		public void setPosition(Long position) {
			this.position = position;
		}

		public List<String> getRowData() {
			return rowData;
		}

		public void setRowData(List<String> rowData) {
			this.rowData = rowData;
		}

		@Override
		public String toString() {
			return parseData();
		}
		
		public String parseData () {
			StringBuilder sb = new StringBuilder();
			
			sb.append("{ position : " + getPosition() + ",");
			sb.append("testRow: [");
			for(String value : getRowData()){
				sb.append("{");
				sb.append("testRowCell:\"");
				sb.append(value);
				sb.append("\"},");
			}
			sb.append("]}");
			return sb.toString();
		}
	}
}
