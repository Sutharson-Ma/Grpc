// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Schema.proto

// Protobuf Java Version: 3.25.6
package com.example.combined.grpc;

public interface FileChunkOrBuilder extends
    // @@protoc_insertion_point(interface_extends:FileChunk)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string file_name = 1;</code>
   * @return The fileName.
   */
  java.lang.String getFileName();
  /**
   * <code>string file_name = 1;</code>
   * @return The bytes for fileName.
   */
  com.google.protobuf.ByteString
      getFileNameBytes();

  /**
   * <code>bytes content = 2;</code>
   * @return The content.
   */
  com.google.protobuf.ByteString getContent();

  /**
   * <code>string session_id = 3;</code>
   * @return The sessionId.
   */
  java.lang.String getSessionId();
  /**
   * <code>string session_id = 3;</code>
   * @return The bytes for sessionId.
   */
  com.google.protobuf.ByteString
      getSessionIdBytes();
}
