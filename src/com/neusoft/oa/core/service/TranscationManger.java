package com.neusoft.oa.core.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.neusoft.oa.core.dao.DBUtil;

public class TranscationManger {
	static public class Transcation {
		private final Connection newConnection;
		private final Connection currentConnection;

		public Transcation(Connection newConnection, Connection currentConnection) {
			super();
			this.newConnection = newConnection;
			this.currentConnection = currentConnection;
		}

		public void commit() {
			try {
				newConnection.commit();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				close();
			}
		}

		public void rollback() {
			try {
				newConnection.rollback();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				close();
			}
		}

		private void close() {
			DBUtil.setCurrentConnection(currentConnection);
			try {
				newConnection.close();
			} catch (Exception e) {
			}
		}
	}

	static public Transcation beginNewTranscation() {
		try {
			Connection currentConnection=DBUtil.getCurrentConnection();
			Connection newConnection = DBUtil.getNewConnection();
			newConnection.setAutoCommit(false);
			DBUtil.setCurrentConnection(newConnection);
			return new TranscationManger.Transcation(newConnection,currentConnection);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
