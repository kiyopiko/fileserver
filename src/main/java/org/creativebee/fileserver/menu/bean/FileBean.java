package org.creativebee.fileserver.menu.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileBean implements Serializable {

	private boolean fileFlg;

	private String filename;

	private Timestamp updateTimestamp;
}
