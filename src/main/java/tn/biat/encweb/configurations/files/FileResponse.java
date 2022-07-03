package tn.biat.encweb.configurations.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class FileResponse {

	  private String name;
	  private String url;
	  private String type;
	  private long size;

}
