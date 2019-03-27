package com.github.damianreeves.ticketbroker.common.filesystem

import java.io.File
import java.nio.file.{Path, Paths}

trait FileSystemOps {
  private lazy val tempFolder:File = {
    val temp = File.createTempFile("temp-file-name", ".tmp")
    temp.getAbsoluteFile.getParentFile
  }

  def tempFolderPath:Path = sys.props.get("java.io.tmpdir") match {
    case Some(path) => Paths.get(path)
    case None => tempFolder.toPath
  }

}

object FileSystemOps extends FileSystemOps
